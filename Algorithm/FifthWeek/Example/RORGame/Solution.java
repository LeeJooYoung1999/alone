package FifthWeek.Example.RORGame;
import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visited = new boolean[n][m];
        int[][] distance = new int[n][m];

        int[] dx = {0, 0, -1, 1};  // 상하좌우
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        distance[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위 확인
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    // 이동 가능하고 방문 안 했으면
                    if (maps[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        distance[nx][ny] = distance[x][y] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        int answer = distance[n - 1][m - 1];
        return answer == 0 ? -1 : answer;
    }//solution
}//class
