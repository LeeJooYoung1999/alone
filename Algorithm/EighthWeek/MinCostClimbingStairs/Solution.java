package EighthWeek.MinCostClimbingStairs;

import java.util.*;

public class Solution {
    // ✅ 불가능한 경우를 판별하기 위한 큰 값 (최소값 비교 시 기준점 역할)
    //final int LIMIT = Integer.MAX_VALUE;  //여기선 출발지부터 도착지 사이에 '도달할 수 없는 계단'이라는 것이 존재하지 않으므로, 쓰이지 않음.

    public int minCostClimbingStairs(int[] cost) {
        // ✅ 메모이제이션을 위한 배열 선언
        // memo[i]는 i번째 계단에 도달하는 최소 비용을 의미
        int[] memo = new int[cost.length + 1];

        // ✅ memo 배열을 모두 초기화 (계산되지 않음을 표시)
        Arrays.fill(memo, -1);

        // ✅ 문제 요구사항에 따라 도달해야 하는 지점은 cost.length (그 다음 계단)
        return dp(cost.length, cost, memo);
    }

    // ✅ dp(n): n번째 계단에 도달하는 최소 비용을 반환
    public int dp(int n, int[] cost, int[] memo) {
        // ✅ 기저 조건: 0번째 또는 1번째 계단에 도달하는 비용은 0
        if(n == 0 || n == 1){return 0;}  // (둘 중 아무데서나 시작 가능)
        // ✅ 이미 계산된 값이 있다면 memo에서 반환
        if(memo[n] != -1) return memo[n];
        // ✅ 점화식 적용:
        // n번째 계단에 도달하는 최소 비용 =
        // min(dp(n-1) + cost[n-1], dp(n-2) + cost[n-2])
        //두 케이스를 나누어 계산.
        int oneStep = dp(n-1, cost, memo) + cost[n-1];  //(한칸전 계단참에 오르기까지 요구되는 최소비용) + (지금 계단 오르는데 들어가는 비용)
        int twoStep = dp(n-2, cost, memo) + cost[n-2];  //(두칸전 계단참에 오르기까지 요구되는 최소비용) + (지금 계단 오르는데 들어가는 비용)
        // ✅ 계산한 값을 memo[n]에 저장한 후 반환
        memo[n] = Math.min(oneStep, twoStep);  //두 케이스중, 더 작은 비용을 저장.
        return memo[n]; //저장한 후 반환.

    }
}

