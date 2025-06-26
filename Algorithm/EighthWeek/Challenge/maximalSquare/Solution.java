package EighthWeek.Challenge.maximalSquare;
import java.util.*;

class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;     //행
        int n = matrix[0].length;  //열
        int maxside = 0;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i-1][j-1]))+1;
                }
                maxside = Math.max(maxside, dp[i][j]);
            }
        }
        return maxside * maxside;
    }
}
