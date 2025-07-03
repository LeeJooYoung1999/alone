package NinethWeek.CarpoolingCost;

import java.util.*;
public class Solution {
    public int solution (int n, int s, int a, int b, int[][] fares){
        //1) 주어진 인풋 간선정보를 순회하며, 양방향그래프에 해당 간선의 운임을 costs[n+1][n+1]에 기록
        //2) s,a,b 세 지점기준, 최소운임을 갖는 합승종료지점 노드를 다익스트라 알고리즘으로 탐색하여, 거리결과 저장배열 distArr[3][n+1]에 저장
        //3) 다익스트라 알고리즘이 종료되어 distArr[3][n+1]에 [[s~n경로들의 최소비용], [a~n경로들의 최소비용], [b~n경로들의 최소비용]]이 담기고 나면,
        //   모든 노드 순회하며(합승종료지점 노드를 바꿔가며), 세 경로비용 합의 최소값 반환.
        //01. 인풋 전처리 : 해야하는것은 3가지 =>
        int[][]costs = new int[n+1][n+1];   //1-index
        for (int[]fare : fares){    //각 간선정보를 순회하며
            costs[fare[0]][fare[1]] = fare[2];//costs[출발->도착] = [운임]
            costs[fare[1]][fare[0]] = fare[2];//양방향(무방향)설정.
        }
        //02. s,a,b 세 지점기준, 최소운임 노드 탐색
        int[][] distArr = new int[3][n+1]; //기준노드3개, 1-index, s,a,b 3지점에서 다익스트라3번 진행용 거리결과 저장배열 distArr
        for (int[] d: distArr){
            Arrays.fill(d, Integer.MAX_VALUE); //, 초기값은 무한대로 설정.
        }
        //03. 다익스트라 알고리즘 3번 진행
        Queue<int[]> pq = new PriorityQueue<>((e1, e2)-> e1[1] - e2[1]); //우선순위 큐 => 최소비용 도출
        final int[] start = {s,a,b}; //다익스트라 시작점 s,a,b

        for(int j=0; j<3; j++){
            int[] d = distArr[j];
            pq.add(new int[]{start[j],0});
            while(!pq.isEmpty()){
                int[] curr = pq.poll();

                for(int i =1; i<=n; i++){
                    if(costs[curr[0]][i] == 0) continue;
                    if(d[i]>curr[1]+costs[curr[0]][i]){ //더 적은비용으로 도달가능하다면?
                        d[i] = curr[1]+costs[curr[0]][i]; //거리비용 배열을 업데이트 해줌.
                        pq.add(new int[]{i,d[i]});
                    }
                }
            }
        }
        //04. 모든 노드 순회하며, 세 경로의 최소비용 반환.
        int minCost = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){ //1번노드 ~ n번노드 들을 합승종료시점 노드로 삼아가며, 운임합 비교.
            int sum = 0;
            for(int[] d: distArr){
                sum+=d[i];  // == sum = distArr[0][i] + distArr[1][i] + distArr[2][i]
            }
            minCost=Math.min(minCost, sum);  //더 낮은비용 반환
        }
        return minCost;
    }
}
