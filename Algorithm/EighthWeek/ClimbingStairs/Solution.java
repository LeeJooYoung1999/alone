package EighthWeek.ClimbingStairs;

class Solution {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];  //
        return dp(n, memo);
    }
    int dp(int n, int[]memo){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        if(memo[n]==0){
        memo[n] = dp(n-1,memo) + dp(n-2,memo);
        }
        return memo[n];
    }
}
