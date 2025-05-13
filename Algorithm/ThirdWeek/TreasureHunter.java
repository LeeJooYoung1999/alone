package ThirdWeek;
import java.util.*;
public class TreasureHunter {
    class Solution {
        public int solution(int n, int m, int[][] hole) {
            //1-1.전처리과정: 방향벡터설정 [상, 하, 좌, 우]
            int[] dx = {0, 0, -1, 1}; //방향벡터 수평성분
            int[] dy = {1, -1, 0, 0}; //방향벡터 수직성분

            //1-2.전처리과정: 지도크기만큼 함정여부를 표시하는 배열
            boolean[][] isHole = new boolean[n+1][m+1];
            for (int[] h : hole){
                isHole[h[0]][h[1]] = true;
            }

            //2.BFS탐색준비
            //2-1 노드탐색용 Queue설정.
            boolean[][][] visited = new boolean[n+1][m+1][2]; //[x][y][0 or 1]: 신발사용여부로 방문여부 체크
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{1,1,0,0}); //현재행, 현재열, 현재시간(=길이), 신발사용여부 {x, y, time, usedShoes(0 or 1)}
            visited[1][1][0] = true;

            //3.BFS탐색시작
            while (!queue.isEmpty()){
                int[] current = queue.poll(); //큐에서 현재 다룰 노드한개 꺼내기
                int currentX = current[0];
                int currentY = current[1];
                int time = current[2];
                int used = current[3];

                if (currentX == n && currentY == m) return time; //보물위치 도달시
                //일반이동 = 1칸이동
                for (int d = 0; d < 4; d++){
                    int nx = currentX + dx[d];
                    int ny = currentY + dy[d];

                    if(nx < 1 || ny < 1 || nx>n || ny>m) continue; //다음탐색할 노드가 지도밖영역으로 삐져나가면 무시
                    if(isHole[nx][ny]) continue; //다음탐색할 노드가 함정이면 무시
                    if(!visited[nx][ny][used]){  //방문하지않은 노드라면,
                        queue.offer(new int[]{nx, ny, time + 1, used}); //다음방문할 로 큐에 추가, 시간+1
                        visited[nx][ny][used] = true; //다음방문할 노드도 이제 방문한것으로 기록하여 중복방문방지
                    }//if
                }//for - 일반이동

                //신발사용이동 = 2칸이동 - 아직 안썼을 때만
                if (used == 0) {
                    for (int d = 0; d < 4; d++){
                        int nx = currentX + 2*dx[d]; //2칸이동
                        int ny = currentY + 2*dy[d];

                        if (nx < 1 || ny < 1 || nx>n || ny>m) continue;
                        if(isHole[nx][ny]) continue;
                        if(!visited[nx][ny][used]){
                            queue.offer(new int[]{nx, ny, time + 1, 1});
                            visited[nx][ny][1] = true;
                        }//if
                    }//for - 신발사용이동
                }//if - 신발사용가능상태
            }//while
            return -1; //도달불가
        }
    }
}
