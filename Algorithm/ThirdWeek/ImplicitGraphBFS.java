package ThirdWeek;

import java.util.*;

public class ImplicitGraphBFS {
    static boolean[][] visited;
    static int[][] grid;
    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
    static int rowLength;
    static int colLength;

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < rowLength && 0 <= c && c < colLength;
    }

    public static void main(String[] args) {
        grid = new int[][] {
                {1, 1, 1, 1},
                {0, 1, 0, 1},
                {0, 1, 0, 1},
                {1, 0, 1, 1}
        };
        rowLength = grid.length;
        colLength = grid[0].length;
        visited = new boolean[rowLength][colLength];
        bfs(0, 0);
    }

    public static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        // queue가 비어있을 때까지 반복
        while (!queue.isEmpty()) {
            // 현재 노드 방문
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];

            // 다음 노드 방문
            for (int i = 0; i < 8; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];
                if (isValid(nextRow, nextCol)) {
                    if (!visited[nextRow][nextCol]) {
                        queue.offer(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }
}
