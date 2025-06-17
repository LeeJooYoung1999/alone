package SeventhWeek.permutation;

import java.util.*;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // main에서 재귀시행하기
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> answer = new ArrayList<>();
        nPr(nums, visited, answer, new ArrayList<>());
        return answer;
    }
    public static void nPr (int[] nums, boolean[] visited, List<List<Integer>> answer, List<Integer> curr) {
        //순열저장하기 : 만들어질 순열을, 이차원배열 answer에 저장.
        if (curr.size() == nums.length) { //재귀종료조건 : 완성된 배열 curr가 입력배열과 길이가 같아질때. r = n = nums.length라서.
            answer.add(new ArrayList<>(curr));  //curr은 재귀이후에 계속 내용이 바뀌는 배열이므로, 복사하여 저장.
            return;
        }
        //순열 구성하기 : 입력배열의 모든숫자 순회하며 방문 안한 숫자를 추가함.
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) { //i번째 원소가 아직 안쓰인 숫자라면,
                visited[i] = true; //방문처리
                curr.add(nums[i]); //순열에 숫자 추가.
                nPr(nums, visited, answer, curr);  //재귀 다음단계 진입
                curr.remove(curr.size() - 1); //이전단계 재귀탈출 후, 백트래킹
                visited[i] = false; //방문여부 초기화. -> 다른순열도 만들어야하니까.
            }
        }
    }
}
