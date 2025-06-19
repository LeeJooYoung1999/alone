package SeventhWeek.N_Queen;
import java.util.*;

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        int[] rows = new int[n];
        int[] cols = new int[n];
        boolean[][] used = new boolean[n][n];
        backtrack(0,0,new ArrayList<>(),answer);
        return answer;
    }
    public void backtrack(int row, int col, List<String> list, List<List<String>> answer) {

    }
}
