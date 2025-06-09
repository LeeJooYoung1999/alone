package FourthWeek.SheepNWolf;
import java.util.*;

public class Solution {
    public int solution(int[] info, int[][] edges){
        //dfs로 풀이
        boolean[] visited = new boolean[info.length]; //방문탐색 저장용 배열
        visited[0] = true; //루트노드인 0번을 방문한 상태로 시작
        return dfs(info,edges,visited,1,0);
    }
    int dfs(int[] info, int[][]edges, boolean[] visited, int sheep, int wolf){
        if(sheep == wolf) return sheep; //무조건 양이 더 많은상태로 시작 + 늑대가 양과 같아지기만 해도 탐색불가임.
        int maxSheep = sheep;

        for(int[] edge : edges){
            final int parent = edge[0]; //부모노드
            final int child = edge[1];  //자식노드

            if(visited[parent] && !visited[child]){ //방문가능노드 탐색  (모든 간선을 확인하여, 현재 방문한 노드(parent)에서 갈 수 있는 미방문 노드(child)탐색)
                visited[child] = true; //방문처리
                if(info[child]==0){ //다음노드가 양이면,
                    maxSheep = Math.max(maxSheep, dfs(info, edges, visited, sheep+1, wolf)); //최대양 업데이트+재귀적 호출통해 다음 노드로 이동.
                } else{
                    maxSheep = Math.max(maxSheep, dfs(info, edges, visited, sheep, wolf+1));
                }
                visited[child] = false; //해당노드를 방문표시 해제(백트래킹) - "자식에서 부모로 다시 거슬러 올라올때" = "더이상 현재 자식들중 더 탐색할수있는것이 없을때"
            }
        }
        return maxSheep;
    }
}
