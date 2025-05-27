package FifthWeek.Example.CoinChange;

import java.util.*;
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) { return 0; } //테케3 예외처리

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); //queue변수 -> 현재금액, 사용동전개수
        boolean[] visited = new boolean[amount+1]; //amount가 0부터 시작이므로 amount+1로 배열크기지정.
        visited[0] = true; //0번노드(0원)부터 시작.

        while ( !queue.isEmpty()) {
            int[] current = queue.poll();
            int curAmount = current[0];
            int coinCnt = current[1];

            for (int coin : coins){
                int newAmount = curAmount + coin;
                if (newAmount == amount) return coinCnt+1;
                if (newAmount < amount+1 && !visited[newAmount]) {
                    visited[newAmount] = true;
                    queue.offer(new int[]{newAmount, coinCnt+1});
                }//if
            }//for
        }//while
        return -1; //완전탐색이 끝나도록 amount에 도달못하면 -1 반환
    }//solution
}//class
