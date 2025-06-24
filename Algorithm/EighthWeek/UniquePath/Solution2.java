package EighthWeek.UniquePath;

import java.util.ArrayList;
import java.util.List;

//접근방법1: 중복조합의 문제로 생각하고 풀기.
public class Solution2 {
    int cnt = 0;
    public int uniquePaths(int m, int n) {
        List<List<Integer>> nHrList = new ArrayList<>();
        nHr(m,n,1, nHrList, new ArrayList<>());
        return cnt;
    }
    public void nHr(int m, int n, int start, List<List<Integer>> nHrList, ArrayList<Integer> curr) {
        if(curr.size() == n-1){
            nHrList.add(new ArrayList<>(curr));
            cnt++;
            return;
        }
        for (int i = start; i <= m; i++) {
            curr.add(i);
            nHr(m, n, i , nHrList, curr);
            curr.remove(curr.size()-1);
        }
    }
}
