package SeventhWeek.combination;
import java.util.*;
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>(); //조합저장용 배열 = 순서중요,오름차순 정렬
        boolean[] visited = new boolean[n];  //방문저장용 배열
        nCr(n,k,visited, answer, new LinkedList<>());
        //오름차순정렬

        return answer;
    }
    public static void nCr(int n, int k, boolean[] visited, List<List<Integer>> answer, List<Integer> curr) { //curr은 각 조합이 저장될 배열이므로, 순서중요X
        if (curr.size() == k) { //재귀종료조건 : 조합의 원소개수가 k개가 됨.
            answer.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true; //방문처리
                curr.add(i);   //현재조합에 i를 원소로 추가.
                nCr(n, k, visited, answer, curr); //재귀다음단계
                curr.remove(curr.size() - 1); //
                visited[i] = false; //방문처리 해제
            }
        }
    }
}
