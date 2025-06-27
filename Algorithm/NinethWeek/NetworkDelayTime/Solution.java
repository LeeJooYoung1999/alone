package NinethWeek.NetworkDelayTime;

import java.util.*;

class Solution {
    class Edge {
        int node;
        int cost;
        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        //그래프변환 (인접리스트로)
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph.get(u).add(new Edge(v, w));
        }
        //다익스트라 알고리즘 (k노드~각 노드로의 최소비용)
        int INF = Integer.MAX_VALUE;
        int[] dists = new int[n+1]; //거리배열초기화: 인덱스 1부터 시작이므로. 배열크기는 n+1
        Arrays.fill(dists,INF);  //"엄청큰수"로 초기화.

        //우선순위 큐 세팅 - 초기값 넣기
        Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        pq.offer(new Edge(k,0));
        dists[k] = 0;  //k노드까지 가는 최솟값으로 초기화.

        while (!pq.isEmpty()) {
            //방문
            Edge cur = pq.poll();
            //예약
            for(Edge next : graph.get(cur.node)){
                int nextDist = dists[cur.node] + next.cost;
                //dists
                if (dists[next.node] > nextDist){
                    pq.offer(new Edge(next.node,nextDist));
                    dists[next.node] = nextDist;
                }
            }
        }

        //최소비용들중에서 가장 큰값을 찾아서 return
        //만약, 도달 못한노드가 하나라도 있다면 -1을 return
        int maxTime =0;
        for(int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, dists[i]);
        }
        return(maxTime==INF)? -1 : maxTime;
    }
}

