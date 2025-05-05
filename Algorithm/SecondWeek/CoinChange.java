package SecondWeek;
import java.util.*;
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0; // 목표 금액이 0이면 동전이 필요 없음

        Queue<int[]> queue = new LinkedList<>(); //큐를 사용해 단계별로 최소 동전개수 탐색(순서X)
        Set<Integer> visited = new HashSet<>();  //Set컬렉션으로 중복탐색 방지

        queue.offer(new int[]{0, 0}); // 초기 상태 (현재 금액: 0, 동전 개수: 0)
        visited.add(0);

        while (!queue.isEmpty()) {
            int[] current = queue.poll(); //BFS 탐색을 진행시, 현재상태 가져오기
            int currentAmount = current[0];
            int coinCount = current[1];

            for (int coin : coins) { //가능한 동전 종류를 순회하면서 새로운 금액 생성
                int newAmount = currentAmount + coin;

                if (newAmount == amount) return coinCount + 1; // 목표 금액에 도달하면 정답(coinCount)반환
                if (newAmount < amount && !visited.contains(newAmount)) {
                    queue.offer(new int[]{newAmount, coinCount + 1}); // 새 상태 저장
                    visited.add(newAmount); // 방문 체크
                }//if
            }//향상된for
        }//while

        return -1; // 목표 금액을 만들 수 없는 경우

    }//coinChange
}//Solution
