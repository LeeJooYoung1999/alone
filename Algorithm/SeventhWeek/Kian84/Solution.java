package SeventhWeek.Kian84;

class Solution {
    private int maxCount;
    public int solution(int balance, int[][] countries) {
        maxCount = 0;
        boolean[] visited = new boolean[countries.length];
        dfs(balance, 0, countries, visited);
        return maxCount;
    }
    public void dfs(int balance, int count, int[][] countries, boolean[] visited) {
        maxCount = Math.max(count, maxCount);
        for (int i = 0; i < countries.length; i++) {
            int airfair = countries[i][0];
            int expense = countries[i][1];
            if(!visited[i] && balance >= expense) {
                visited[i] = true;
                dfs(balance - airfair, count+1, countries, visited);
                visited[i] = false;
            }
        }
    }
}
