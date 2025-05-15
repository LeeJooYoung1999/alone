package ThirdWeek;
import java.util.*;

public class SocialDistancing {
    class Solution {
        public int[] solution(String[][] places) {
            int[] answer = new int[5]; //places의 행길이 = 대기실개수 = 5개

            for (int i = 0; i < 5; i++) {//대기실 5개이므로, 5회 검사시행
                if(checkRoom(places[i])){
                    answer[i] = 1; //검사결과: 통과
                } else {
                    answer[i] = 0; //검사결과: 거리두기 미준수
                }//else
            }//for - 5회검사
            return answer;
        }

        //대기실 검사메소드 checkRooms정의
        public boolean checkRoom(String[] room) {
            for (int i=0; i<5; i++ ){ //가로5
                for(int j=0; j<5; j++){//세로5인 크기의 대기실좌석을 탐색하여,
                    if(room[i].charAt(j)=='P') { //만약 해당좌석이 응시자가 앉은자리면,
                        if(!isSafe(room, i, j)){ //i열 j석을 기준으로 거리두기준수여부 검사.
                            return false; //한명이라도 안지켜졌으면 false반환
                        }
                    }//if
                }//for
            }//for
        return true; //전원 통과해야지만 true반환
        }//checkRooms 메소드

        //isSafe : 거리두기 위반감지용 메소드(BFS사용)
        public boolean isSafe(String[] room, int X, int Y){
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[5][5]; //방은 5X5고정크기
            //방향벡터설정 [상, 하, 좌, 우]
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            queue.offer(new int[]{X, Y, 0}); //시작x 시작y 이동거리dist
            visited[X][Y] = true;

            while(!queue.isEmpty()) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];
                int dist = curr[2];
                // 시작점이 아닌데(P 자신 제외) 거리 2 이하에서 또 다른 P를 만나면 거리두기 위반
                if (dist >= 1 && dist <= 2 && room[currX].charAt(currY) == 'P') {
                    return false;
                }//거리두기 위반 발견시 바로 false반환
                for (int d = 0; d < 4; d++) {
                    int nx = currX + dx[d];
                    int ny = currY + dy[d];
                    //다음탐색노드
                    if(nx>=0 && ny>=0 && nx<5 && ny<5 && !visited[nx][ny]) {
                        if (room[nx].charAt(ny) != 'X') {//파티션 아닐때
                            queue.offer(new int[]{nx, ny, dist+1}); //큐에 추가
                            visited[nx][ny] = true; //방문처리
                        }//if
                    }//if
                }//for - 탐색
            }//while - BFS
            return true; //전원 거리두기위반X
        }//isSafe메소드
    }
}
