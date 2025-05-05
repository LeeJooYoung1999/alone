package SecondWeek;
import java.util.*;
public class CanVisitAllRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size(); //방의 개수 n
        boolean[] visited = new boolean[n]; //방문여부체크
        Queue<Integer> queue = new LinkedList<>(); //Queue를 통한 BFS탐색

        //초기상태설정
        visited[0] = true;
        queue.offer(0); // 방 0부터 시작

        //BFS탐색진행
        while (!queue.isEmpty()) {
            int room = queue.poll();

            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true; //방문하지 않은방이면 방문처리
                    queue.offer(key);   //큐에 추가하여 다음 탐색대상으로 설정
                }//if - 새로운방 방문처리
            }//향상된for - 현재방의 열쇠뭉치 순회
        }//while - BFS탐색

        // 모든 방을 방문했는지 확인
        for (boolean v : visited) {
            if (!v) return false; //모든방을 방문하는데 실패하였을 경우 false반환
        }
        return true;  //모든방을 방문하는데 성공하였을 경우 true반환
    }//canVisitAllRooms
}//solution
