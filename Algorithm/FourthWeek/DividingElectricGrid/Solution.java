package FourthWeek.DividingElectricGrid;
import java.util.*;
public class Solution {
    int answer=-1;
    public int solution(int n, int[][] wires) {
        //1. 주어진 간선정보 wires[][]를  인접리스트 형태graph로 가공
        answer = n; // 처음엔 최대 차이값(n)으로 초기화
        //각 노드번호별로 노드리스트를 해시맵에 저장.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i <= n; i++) { //노드번호를 1부터 사용하기 위해 n+1번 반복
            graph.put(i, new ArrayList<>()); //그래프 초기화
        }
        //간선을 양방향으로 연결
        for(int[]wire: wires){
            graph.get(wire[0]).add(wire[1]); //그래프를
            graph.get(wire[1]).add(wire[0]); //양방향으로 구성.
        }

        //모든 간선을 하나씩 끊어보며 차이 계산
        for (int[] wire: wires) {
            //해당 wire끊기 (전체 트리를 두개의 서브트리로 분해)
            graph.get(wire[0]).remove(Integer.valueOf(wire[1]));
            graph.get(wire[1]).remove(Integer.valueOf(wire[0]));

            boolean[] visited = new boolean[n+1];
            dfs(graph, visited, wire[0], n);

            //끊었던 wire 복구
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }
        return answer;
    }//soution

    //2. DFS함수를 정의하여,
    //   2-1. (재귀호출로) 연결된 노드의 수를 카운트(count)하고,
    //   2-2. 두 네트워크 간 노드 수 차이를 계산(n- count*2),
    //   2-3. Math.min이용하여 (n-2*count)를 answer에 최솟값으로 갱신
    int dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int current, int n) { //인접리스트형태의 그래프, 방문여부저장용 배열, 현재노드, 모든노드개수
        int count = 1;  //노드의 개수는 1개부터 시작
        visited[current] = true; //방문처리

        for (int next : graph.get(current)) {
            if (!visited[next]) {                       //인접노드가 아직 방문하지 않은 노드라면,
                count += dfs(graph, visited, next, n);  //재귀 dfs호출 (하위서브트리의 노드개수를 모두 누적합산)
            }
        }
        //2-2, 2-3 : 최적값(=두 전력망의 노드수가 최대한 비슷한 값)의 업데이트
        answer = Math.min(answer, Math.abs(n - count * 2));
        //자신 아래의 노드수를 재귀적으로 반환
        return count;
    }
}//class