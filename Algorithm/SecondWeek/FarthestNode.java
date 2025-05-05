package SecondWeek;
import java.util.*;
public class FarthestNode {
    public int solution(int n, int[][] edge) {
        //주어진 edge[][]vertex를 인접리스트 형식의 그래프로 구성.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { //0번은 쓰지않으나, 공간확보위해 n+1개
            graph.add(new ArrayList<>());
        }
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]); // 이웃등록(양방향)
        }

        //거리와 방문여부 저장용 배열
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];

        //BFS탐색
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); //1번노드부터 시작
        visited[1] = true; //방문한곳은 true
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {  //미방문 노드시,
                    visited[neighbor] = true; //방문노드에 추가하고,
                    distance[neighbor] = distance[current] + 1; //거리저장배열에 1더하고,
                    queue.offer(neighbor); //다음방문대상 큐에 추가.
                }//if - 인접노드 방문했는지 검사
            }//향상된 for문
        }//while

        //최대거리연산
        int maxDistance = 0;
        for (int d : distance) {
            maxDistance = Math.max(maxDistance, d);
        }
        //가장 먼 노드의 개수 세기(answer)
        int answer = 0;
        for (int d : distance) {
            if (d == maxDistance) {
                answer++;
            }
        }
        return answer;
    }//solution
}//클래스
