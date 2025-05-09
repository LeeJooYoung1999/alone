package ThirdWeek;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMat {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int shortest = -1; //최종반환하고자 하는 최단거리

        //1. 방향벡터 설정(상하좌우+대각: 8방향)
        int rowLen = grid.length; //주어진 그리드의 행 길이
        int colLen = grid[0].length; //주어진 그리드의 열 길이
        boolean[][] visited = new boolean[rowLen][colLen]; //방문저장용배열 생성
        int[] dr = {-1, 1, 0, 0, 1, 1, -1, -1}; //방향벡터 수평성분
        int[] dc = {0, 0, -1, 1, 1, -1, 1, -1}; //방향벡터 수직성분

        //예외처리: 시작(0,0) 혹은 끝점(n-1,n-1)의 값이 1이면 바로-1반환하고 종료(clear path가 존재하지 않음)
        if (grid[0][0]==1 || grid[rowLen-1][colLen-1]==1) return -1;

        //2.BFS탐색 준비
        //2-1노드탐색용 Queue설정
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,1}); //현재행, 현재열, 현재길이(방문한노드개수)
        visited[0][0] = true;

        //3.BFS탐색시작
        while (!queue.isEmpty()) { //큐가 다 빌때까지 반복
            int[] current = queue.poll(); //큐에서 현재 다룰 노드하나 꺼내기
            int currentRow = current[0]; //현재행
            int currentCol = current[1]; //현재열, 둘이합쳐 현재위치정보
            int currentDistance = current[2]; //현재이동거리정보

            //목적지[rowLen-1][colLen-1] 도착시 종료하도록 조건문작성
            if (currentRow == rowLen-1 && currentCol == colLen-1) {
                shortest = currentDistance; //정상종료시점에서의 현재거리 = 최단거리
                break; //BFS탐색종료
            }//if - 도착시종료 조건문

            //다음노드탐색용 반복문
            //방향벡터이용 팔방 검사
            for (int i = 0; i<8; i++){ //8방위 방향벡터 탐색
                int nextRow = currentRow+dr[i]; //다음탐색할 노드의 행 = 현재노드의 행 +(8방위 행)
                int nextCol = currentCol+dc[i]; //다음탐색할 노드의 열 = 현재노드의 열 +(8방위 열)

                //그래프탈주방지+재방문방지용 조건문
                if(nextRow >=0 && nextRow <rowLen && nextCol>=0 && nextCol <colLen){ //정해진 그리드 안에서 놀아라(경계값)
                    if(!visited[nextRow][nextCol]){ //방문 안한데 중에 골라라
                        if(grid[nextRow][nextCol]==0){ //0이 길, 1은 장애물
                            queue.offer(new int[]{nextRow,nextCol,currentDistance+1}); //다음방문할 위치(next행, next열)를 큐에 추가
                            visited[nextRow][nextCol] = true; //다음방문할 노드도 이제 방문한것으로 기록하여 중복방문방지
                        }//if - 경로로 삼을수 있는 노드는 0값을 갖는 노드
                    }//if - 재방문방지
                }//if - 그래프탈주방지
            }//for - 다음노드탐색
        }//while

    return shortest; //최단경로 반환(경로가 없으면 -1반환)
    }//solution
}//class
