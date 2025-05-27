package FifthWeek.Example.ElectricGridDeviding;
import java.util.*;
class Solution {
    public  int solution(int n, int[][] wires){
        int answer = Integer.MAX_VALUE; //'더 작은것 고르기' Math.min위해 '적당히 큰수'로 정의
        // 해시맵을 이용해, 주어진 간선정보를 <노드, <해당 노드와 이어진 노드>>그래프, 즉 인접리스트 그래프로 정리한다.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i=0;i<=n; i++) {
            graph.put(i, new ArrayList<>()); //i노드와 연결된 노드리스트를 ArrayList자료구조를 이용해 관리.
        }//for
        
    }//sol
}//class
