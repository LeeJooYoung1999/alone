package FifthWeek.Practice.BeadsEscape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //큐에 사용할 상태클래스
    static class State{
        int rx,ry,bx,by,depth;
        public State(int rx, int ry, int bx, int by, int depth){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }
    //전역변수 선언
    static int n, m;
    static char[][] board;
    static boolean[][][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //구슬이동함수 move
    static int[] move(int x, int y, int dx, int dy){
        int count =0;
        while (board[x+dx][y+dy] !='#' && board[x][y] != 'O'){
            x+=dx;
            y+=dy;
            count++;
            if(board[x][y]=='O') break; //구멍에 위치하면 함수종료.
        }
        return new int[]{x,y,count}; //동작이후 좌표와 이동칸수 반환. & 이동칸수는 예외처리위함.
    }

    //완전탐색함수 bfs()
    public static int bfs(int rx, int ry, int bx, int by){
        Queue<State> queue = new LinkedList<>();
        visited[rx][ry][bx][by] = true;
        queue.offer(new State(rx, ry, bx, by, 0));
        while (!queue.isEmpty()){
            State cur = queue.poll();
            if(cur.depth>10) return 0; // 완전탐색 종료조건:10번이내에 탈출실패시.

            //이동시켜 탐색
            for (int dir = 0; dir<4; dir++){
                //r과 b에 각각 구슬이동함수move 적용
                int[] rMove = move(cur.rx, cur.ry, dx[dir], dy[dir]);
                int[] bMove = move(cur.bx, cur.by, dx[dir], dy[dir]);
                //동작후 r과 b에 move() 반환값 담기.
                int nrx = rMove[0], nry = rMove[1], rCount= rMove[2];
                int nbx = bMove[0], nby = bMove[1], bCount= bMove[2];

                //예외처리
                if (board[nbx][nby]=='O') continue; //어떤경우든 파란구슬 빠지면 실패
                if (board[nrx][nry]=='O') return 1; //빨간구슬만 빠지면 성공

                if(nrx == nbx && nry == nby){ //빨간구슬과 파란구슬의 도착지가 겹치는경우.
                    if(rCount > bCount){ //이동거리더 큰놈 == 더 나중에 도달하는놈
                        nrx -= dx[dir];
                        nry -= dy[dir]; //온방향으로 한칸 후퇴
                    } else{
                        nbx -= dx[dir];
                        nby -= dy[dir];
                    }
                }
                //이동종료 후, 방문처리
                if(!visited[nrx][nry][nbx][nby]){
                    visited[nrx][nry][nbx][nby] = true;
                    queue.offer(new State(nrx,nry,nbx,nby,cur.depth+1));
                }
            }
        }
        return 0; //완전탐색 완료시에도 탈출실패.
    }
    public static void  main (String[] args) throws IOException {
        // 백준 입력부 처리
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in)); //입력받겠습니다.
        StringTokenizer st = new StringTokenizer(br.readLine()); //한줄씩 문자열로 읽겠습니다. 또, 공백기준으로 쪼개겠습니다.
        n = Integer.parseInt(st.nextToken()); //첫번째 int는 n입니다.
        m = Integer.parseInt(st.nextToken()); //두번째 int는 m입니다.
        board = new char[n][m]; //n*m사이즈의 board
        visited = new boolean[n][m][n][m]; //visited배열 사이징

        int rx=0,ry=0,bx=0,by=0; //두 구슬 임시초기값
        for (int i=0; i<n; i++){ //구슬판 행 = n칸
            String line = br.readLine(); //주어진 구슬판지도는 문자열, 줄별로 읽는다.
            for (int j=0; j<m; j++){//구슬판 열 = m칸
                board[i][j] = line.charAt(j);
                if(board[i][j]=='R'){
                    rx=i;
                    ry=j;
                    board[i][j] = '.'; //R의 위치는 rx, ry에 담아뒀으니, 빈칸으로 변경
                }
                if(board[i][j]=='B'){
                    bx=i;
                    by=j;
                    board[i][j] = '.'; //B도 마찬가지.
                }
            }
        }
        System.out.println(bfs(rx, ry, bx, by));
    }
}
