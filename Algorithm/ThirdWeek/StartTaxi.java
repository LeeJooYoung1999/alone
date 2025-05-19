package ThirdWeek;
import java.util.*;
import java.io.*;
public class StartTaxi {
    //방향벡터 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N,M,fuel; //N은 도시의 크기, M은 승객의 수
    static int[][] map;
    static Taxi taxi;
    static List<Passenger> passengers = new ArrayList<>(); //passengers는 ArrayList로 관리
    //메인 실행함수
    public static void main(String[] args) throws IOException {
        inputReader(); //입력처리 및 초기설정

        for (int i = 0; i < M; i++) { //승객 수만큼 반복
            Passenger target = findNearestPassenger(); //현재택시위치 기준, 가장 가까운 승객을 찾아 다음 타겟승객으로 설정.
            if(target == null) { //승객을 더이상 찾을수 없으면 종료
                return;
            }
            // 택시 --> 승객(출발지)
            int toPassenger = move(taxi.x, taxi.y, target.sx, target.sy); // 택시 to 승객(taget)BFS 실행
            if(toPassenger == -1 || toPassenger > fuel) { //이동 불가능하면 종료
                return;
            }
            fuel -=toPassenger;                 //택시의 잔여연료량이 승객위치까지 이동하는데 소모된것 최신화.
            taxi.moveTo(target.sx, target.sy);  //택시의 현재좌표를 타겟의 출발지로 최신화
            //----------------------------------------//

            //승객(출발지) --> 승객(목적지)
            int toDestination = move(target.sx, target.sy, target.ex, target.ey);
            if(toDestination == -1 || toDestination>fuel) { //이동 불가능하면 종료
                return;
            }
            fuel -= toDestination; //출->목 연료 차감
            fuel += (toDestination)*2; //목적지 도착 연료재충전
            taxi.moveTo(target.ex, target.ey); //택시의 좌표정보 업데이트
            passengers.remove(target); //목적지 도달한 승객은 목록에서 제거
            //-----------------------------------------//
        }//for - 승객수만큼 시행
    }//psvm
    //메소드정의1 - 입력값 처리기 inputReader()
    static void inputReader()  throws IOException{
        //입력스트림 설정하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //기본 변수 입력받아 저장하기.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        //맵 크기정보+형상정보 입력받아 저장하기 (0은 빈칸, 1은 벽)
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //택시의 초기위치 입력받아 설정하기
        st = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(st.nextToken()) - 1; // 0-indexed
        int ty = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Taxi(tx, ty);

        //초기 M명의 승객정보를 입력받아, Passenger리스트에 (출발지,목적지)저장하기.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            passengers.add(new Passenger(sx, sy, ex, ey));
        }
    }
    //메소드정의2 - BFS활용1: 가장 가까운 승객 탐색하는 findNearestPassenger()
    static class PassengerInfo implements Comparable<PassengerInfo>{ //Comparable를 구현하여 승객정보+거리를 담아내는 클래스
        //target이 될 승객 선정기준 거리dist > 행sx > 열sy 작은순
        Passenger passenger;
        int dist;

        PassengerInfo(Passenger passenger, int dist) {
            this.passenger = passenger;
            this.dist = dist;
        }

        @Override
        public int compareTo(PassengerInfo o) {
            if (this.dist != o.dist) return Integer.compare(this.dist, o.dist);
            if (this.passenger.sx != o.passenger.sx) return Integer.compare(this.passenger.sx, o.passenger.sx);
            return Integer.compare(this.passenger.sy, o.passenger.sy);
        }
    }
    static Passenger findNearestPassenger(){
        // BFS로 가장 가까운 승객 탐색
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        List<PassengerInfo> candidates = new ArrayList<>();

        queue.add(new int[]{taxi.x, taxi.y, 0}); //큐에 x, y, 거리 저장.
        visited [taxi.x][taxi.y] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            for (Passenger p : passengers) {
                if(p.sx == x && p.sy == y){
                    candidates.add(new PassengerInfo(p,dist));
                }//if
            }//for
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx<0 || ny<0|| nx>=N||ny>=N) continue;
                if(map[nx][ny] ==1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny,dist+1});
            }//for
        }//while
        if(candidates.isEmpty()) return null;
        Collections.sort(candidates); //정렬기준에 맞춰 정렬(우선순위)
        return candidates.get(0).passenger; //가장 우선순위가 높은 승객을 리턴
    }
    //메소드정의3 - BFS활용2: 택시기동
    static int move(int x, int y, int sx, int sy) {
        // BFS로 시작(sx, sy) -> 목적지(ex, ey)까지 최단거리 반환
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y,0}); //x,y,거리
        visited[x][y] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curx = cur[0];
            int cury = cur[1];
            int dist = cur[2];
            for (int d=0; d < 4; d++) {
                int nx = curx + dx[d];
                int ny = cury + dy[d];
                if(nx<0 || ny<0|| nx>=N||ny>=N) continue;
                if(map[nx][ny] ==1 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny,dist+1});
            }
        }
        return -1;
    }
    //클래스 정의
    static class Taxi{
        int x,y; //택시 현재위치
        Taxi(int x, int y){ //택시의 현재위치 검색 메소드
            this.x = x;
            this.y = y;
        }
        void moveTo(int nx, int ny){ //택시의 현재위치 최신화 메소드
            this.x = nx;
            this.y = ny;
        }
    }
    static class Passenger{ //승객의 (출발지,목적지) 저장하는 클래스
        int sx, sy,ex,ey;
        Passenger(int sx, int sy, int ex, int ey){
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
    }
}//class - solution
