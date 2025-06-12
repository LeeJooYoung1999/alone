package FifthWeek.Practice.BeadsEscape;
import java.io.*;
import java.util.*;

//Solution 클래스
public class SolGpt {
    //큐에 사용할 상태클래스 State
    static class State {
        int rx, ry, bx, by, depth;

        public State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }
    //전역변수 설정.
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};

    //기울임에 따른 구슬이동함수 move
    static int[] move(int x, int y, int dx, int dy) {
        int count = 0;
        while (board[x + dx][y + dy] != '#' && board[x][y] != 'O') {
            x += dx;
            y += dy;
            count++;
            if (board[x][y] == 'O') break;
        }
        return new int[]{x, y, count};
    }

    //완전탐색을 위한 BFS정의
    public static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        visited[rx][ry][bx][by] = true;
        queue.offer(new State(rx, ry, bx, by, 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.depth >= 10) return 0;

            for (int dir = 0; dir < 4; dir++) {
                int[] rMove = move(cur.rx, cur.ry, dx[dir], dy[dir]);
                int[] bMove = move(cur.bx, cur.by, dx[dir], dy[dir]);

                int nrx = rMove[0], nry = rMove[1], rCount = rMove[2];
                int nbx = bMove[0], nby = bMove[1], bCount = bMove[2];

                // 파란 구슬이 구멍에 빠지면 실패
                if (board[nbx][nby] == 'O') continue;

                // 빨간 구슬만 빠졌다면 성공
                if (board[nrx][nry] == 'O') return 1;

                // 같은 위치에 있다면 이동 거리 비교하여 조정
                if (nrx == nbx && nry == nby) {
                    if (rCount > bCount) {
                        nrx -= dx[dir];
                        nry -= dy[dir];
                    } else {
                        nbx -= dx[dir];
                        nby -= dy[dir];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.offer(new State(nrx, nry, nbx, nby, cur.depth + 1));
                }
            }
        }

        return 0;
    }

    //실행부 main
    public static void main(String[] args) throws IOException {
        //백준 입력부 처리.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                    board[i][j] = '.'; //R의 위치는 rx, ry에 담아뒀으니, 빈칸으로 변경
                }
                if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                    board[i][j] = '.'; //B도 마찬가지.
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }
}

