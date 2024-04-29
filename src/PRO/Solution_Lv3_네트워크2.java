package PRO;

class Solution_Lv3_네트워크2 {
    static boolean[] visited;
    static int answer;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, n, computers);
                answer++;
            }
        }
        return answer;
    }
    public void dfs(int node, int n, int[][] computers) {
        visited[node] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                dfs(i, n, computers);
            }
        }
    }
}