package PRO;

class Solution_Lv2_피로도 {
    static int ans;
    static boolean[] visited;
    static int N;
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        visited = new boolean[N];
        dfs(0, k, dungeons);
        return ans;
    }
    public void dfs(int idx, int k, int[][] dungeons) {
        if (idx <= N) {
            ans = Math.max(idx, ans); // idx가 개수를 의미하기도 함
        }
        if (idx == N) {
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(idx + 1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }
}