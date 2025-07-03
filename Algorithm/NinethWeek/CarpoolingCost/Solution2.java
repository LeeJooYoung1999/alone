package NinethWeek.CarpoolingCost;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution2 {
    public int solution (int n, int s, int a, int b, int[][] fares){
        //01. 인풋전처리 -> 양방향 그래프화
        int[][]costs = new int[n+1][n+1]; //1-index 이차원배열 : 각 지점간 운임 기록용.
        for (int[] fare : fares) {
            costs[fare[0]][fare[1]] = fare[2];  //출발->도착 == 운임
            costs[fare[1]][fare[0]] = fare[2];
        }
        int[][]distArr = new int[3][n+1];
        for (int[] d: distArr) {
            Arrays.fill(d, Integer.MAX_VALUE); //초기값설정.
        }
        //다익스트라 알고리즘
        Queue<int[]> pq = new PriorityQueue<>((e1,e2)-> e1[1]-e2[1]);
        final int[] start = {s,a,b};

        for (int j=0; j<3; j++){
            int[]d = distArr[j];
            pq.add(new int[]{start[j],0});
            while(!pq.isEmpty()){
                int[] curr = pq.poll();
                for(int i=1; i<=n; i++){
                    if(costs[curr[0]][i]==0) continue; //curr의 첫번째 원소는, start[j]
                    if(d[i]>curr[1]+costs[curr[0]][i]){
                     d[i] = curr[1]+costs[curr[0]][i];
                     pq.add(new int[]{i,d[i]});
                    }
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int i=0; i<=n; i++){
            int sum = 0;
            for(int[] d: distArr){
                sum +=d[i];
            }
            minCost = Math.min(minCost,sum);
        }
        return minCost;
    }
}
