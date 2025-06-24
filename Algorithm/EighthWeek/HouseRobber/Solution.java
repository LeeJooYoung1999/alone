package EighthWeek.HouseRobber;

import java.util.Arrays;

public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n]; //nums도 0인덱스, memo도 0인덱스, 둘다 단순배열이니까 괜찮.
        Arrays.fill(memo, -1); //초기값은 불가능한수 -1로 채워넣기.
        return dp(nums, n-1, memo); //nums는 0부터 n-1번지까지, n개의 집이 늘어서있음.
    }

    public int dp(int[] nums, int i, int[] memo) {
        //--기저조건--//
        if (i < 0) return 0; // 더 이상 털 집이 없음
        if (i == 0) return nums[0]; // 첫 번째 집만 털 수 있음
        //--점화식 w.메모이제이션--//
        if (memo[i] != -1) return memo[i]; //메모에 있을경우 재사용.
        memo[i] = Math.max(dp(nums, i-1, memo), dp(nums, i-2, memo)+nums[i]); //없으면 새로 정의해 저장.
        return memo[i]; //새로 저장한 memo[i] 반환하기.
    }
}
