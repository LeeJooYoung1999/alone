package EighthWeek.MinCostClimbingStairs;

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {

        }
    }
    int dfs(int n){
        return Math.min(dfs(n-1)+cost[n-1], dfs(n-2)+cost[n-2]);
    }
}
