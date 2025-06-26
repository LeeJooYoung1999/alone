package EighthWeek.LCSubseq;

import java.util.*;
public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];  //문자열의 길이가 0인곳까지 인덱스로 처리하기 위해 m+1, n+1크기로 생성.

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 문자 같을 때, 이전까지의 LCS 길이 dp[i-1][j-1]에 1을 더해서 저장
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 다를 때, 현재 문자를 포함하지 않는 두 가지 경우 중, 더 긴 쪽을 선택
                }
            }
        }
        return dp[m][n]; // LCS의 최종 길이
    }
}

