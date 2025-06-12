package FifthWeek.Example.ElectricGridDividing;

import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution (int n, int[][] wires){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i =1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }//for - graph
        for (int[] wire: wires){
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }//for - 양방향연결

        boolean[] visited = new boolean[n+1];
        dfs(graph, visited, 1, n); //노드는 1번부터 시작.
        return answer;
    }//solution
    int dfs (Map<Integer, List<Integer>> graph, boolean[] visited, int cur, int n){
        int count = 1;
        visited[cur] = true;
        for(int next : graph.get(cur)){
            if(!visited[next]){
                count += dfs(graph, visited, next, n);
            }//if 미방문노드이면
        }//for cur근접노드
        answer = Math.min(answer, Math.abs(n-count*2)); //최적의 answer비교업데이트
        return count; //재귀적으로, 자기가 속한 서브트리의 노드수 반환.
    }//dfs
}//class
