package EighthWeek.CoinChange;
import java.util.*;

public class Solution {
    final int LIMIT = Integer.MAX_VALUE;  //불가능한 상황을 표현하는 큰 수 LIMIT정의하여 표현, (불가능한상황을 수학적으로 비교가능한 형태로 표현하기위해, 구태여 이렇게 표현한것.)
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1]; //최소동전개수 저장용 (메모이제이션용) 배열 memo를 총액수 amount+1(인덱스는0부터시작하므로 1더하기) //memo 인덱스: 0~amount
        Arrays.fill(memo, LIMIT); //memo배열의 모든 성분을 불가능한 큰수 LIMIT으로 초기화하여, Math.min으로 비교하기 용이하게함.
        return dp(memo, coins, amount);
    }
    public int dp(int[] memo, int[] coins, int amount) {
        if(amount ==0) return 0;  //기저조건 : amount가 0이면 교환가능한 동전0개를반환하고 early return (testcase 3번.)

        int result = LIMIT; //큰문제를 풀기위한 작은문제, '남은 금액에 대한 조합가능한 동전개수는?'의 반환값을 저장하기위한 변수 result에 마찬가지로 불가능한 상황을 표현하는 큰 수 LIMIT을 사용하여 초기화.
        // coins의 각 동전을 살펴보며 현재 총액에서 깎아낼수 있는 동전종류를 파악하여 조합에 추가 -> 해당조합의 동전수와 reult비교하여, 최소값으로 업데이트. : 이를 재귀적으로 적용하면, 모든 조합을 고려가능함.
        for (int coin : coins){
            if(amount-coin >=0) {//현재 동전을 사용하고 남은금액이 양수라면,
                if(memo[amount-coin]==LIMIT){ //그 와중에 남은금액에 대한 최소조합동전개수가 memo에 저장되어있지 않다면?
                    memo[amount-coin] = dp(memo, coins, amount-coin); //남은금액에 대해 재귀함수를 호출하고, 반환값을 memo에 저장.
                }
                if(memo[amount-coin] != -1){//그렇지 않고 남은 금액에 대한 최소조합동전개수가 사전에 계산되어 memo에 저장되어있는 상태라면?
                    result = Math.min(result, memo[amount-coin]); //남은금액에 대한 조합가능한 최소동전개수 비교하여 result에 업데이트하기.
                }
            }
        }
        //조합가능한 동전최소개수가 존재한다면 1을 더하여 반환. (최종적으로 도출된 result는 남은금액에 대한 조합가능한 최소동전개수이므로, '현재 동전' 1개도 더해주어야함.)
        return (result == LIMIT) ? -1 : result + 1;
    }
}
