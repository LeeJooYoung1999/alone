package ThirdWeek;

public class NumIsland {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 그리드 바깥이거나, 물(0)이면 return
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }

        // 방문 처리 (1 → 0)
        grid[i][j] = '0';

        // 상하좌우 방문
        dfs(grid, i - 1, j); // 위
        dfs(grid, i + 1, j); // 아래
        dfs(grid, i, j - 1); // 왼쪽
        dfs(grid, i, j + 1); // 오른쪽
    }
}
