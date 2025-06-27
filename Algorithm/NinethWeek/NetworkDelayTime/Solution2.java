package NinethWeek.NetworkDelayTime;
import java.util.*;
import java.util.stream.Collectors;

class Solution2 {
    public int networkDelayTime(int [][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = Arrays.stream(times).collect(Collectors.groupingBy(t -> t[0]));
        int[] visited = new int[n+1]; //visited[i]는 노드i까지 도달하는데 걸린 최단시간.
        Arrays.fill(visited, -1);  //-1번째 노드는 없는것. 아직 방문하지 않았음을 표시하기에 좋음. 다른방법으로는 충분히 큰수(Integer.MAX_VALUE)
        Queue<int[]>pq = new PriorityQueue<>((e1,e2) -> e1[1] - e2[2]); //우선순위큐 pq, 여기들어가는 int[]는 [현재 노드번호, 현재까지의 누적거리]를 의미함. 두 배열을 비교할때, 시간기준으로 작은순서대로 정렬.
        pq.add(new int[]{k,0});  //시작노드 k에서, 거리0으로 시작.
        visited[k] = 0;

        int maxTime = 0;    //최단거리중 최대값
        int visitCount = 1; //방문노드개수
        //다익스트라 핵심
        while(!pq.isEmpty()){
            int[] cur = pq.poll(); //우선순위 큐에서 하나 꺼내기. -> 현재방문노드 삼기.
            int u = cur[0], time = cur[1];
            if (visited[u] < time) continue;  //만약 현재까지의 누적거리가
        }


        return -1;
    }
}
