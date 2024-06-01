package PRO;

import java.util.*;
class Solution_Lv2_석유시추 {
    static boolean[][] visited;
    static int[] arr;
    static int N, M;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        arr = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    bfs(i, j, land);
                }
            }
        }
        // System.out.println(Arrays.toString(arr));
        int answer = arr[0];
        for (int i = 1; i < M; i++) {
            answer = Math.max(answer, arr[i]);
        }
        return answer;
    }
    public void bfs(int x, int y, int[][] land) {
        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> col = new HashSet<>();
        q.add(new int[] {x, y});
        col.add(y);
        visited[x][y] = true;
        int count = 1;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i=0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || land[nx][ny] == 0) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new int[] {nx, ny});
                col.add(ny);
                count++;
            }
        }
        // 열에 해당하는 arr 배열에 count 개수 할당인데 기존의 것이랑 더하기
        for (Integer c: col) {
            arr[c] += count;
        }
    }
}