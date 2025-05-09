package ThirdWeek;
import java.util.*;
public class MazeEscape {
    public int solution(String[] maps) {
        //1-1.전처리과정: 문자열배열 maps[]를 2차원배열 grid[][]로 변환
        char[][] grid = new char[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            grid[i] = maps[i].toCharArray();
        }

        //1-2.전처리과정: 시작점(S), 레버(L), 출구(E)의 위치찾기
        int startX = -1, startY = -1;
        int leverX = -1, leverY = -1;
        int exitX = -1, exitY = -1;
        int rowLen = grid.length; //행의 길이 = 문자열의 개수
        int colLen = grid[0].length; //열의 길이 = 문자열내 문자의 개수
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 'S') { //출발점
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 'L') { //레버
                    leverX = i;
                    leverY = j;
                } else if (grid[i][j] == 'E') { //출구
                    exitX = i;
                    exitY = j;
                }//if-else if-else
            }//for inner
        }//for outer

        // 3.BFS 두번 시행 S -> L , L -> E
        int sToL = bfs(grid, startX, startY, leverX, leverY);
        int lToE = bfs(grid, leverX, leverY, exitX, exitY);

        //4. 예외처리: 두번 시행과정에서, 하나라도 경로가 막히면 실패
        if(sToL==-1 || lToE==-1) return -1;

        return sToL + lToE;
    }
    //2.BFS함수 정의(2회사용을 위한 재사용성)
    public static int bfs (char[][] grid, int startX, int startY, int targetX, int targetY){
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        //2-1방향벡터 설정(상하좌우 4방향)
        int[] dr = {0, 0, -1, 1}; //방향벡터 수평성분 상, 하, 좌, 우
        int[] dc = {1, -1, 0, 0}; //방향벡터 수직성분 상, 하, 좌, 우

        //2-2노드탐색용 Queue설정
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0}); //현재행 현재열, 현재길이
        visited[startX][startY] = true;

        //2-3 BFS탐색시작
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curDist = cur[2];

            if (curRow == targetX && curCol == targetY) return curDist;

            for(int i = 0; i<4; i++){
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (nextRow >=0 && nextCol>=0 && nextRow<n && nextCol<m && !visited[nextRow][nextCol] && grid[nextRow][nextCol] !='X'){
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol, curDist+1});
                }
            }
        }
        return -1;
    }
}
