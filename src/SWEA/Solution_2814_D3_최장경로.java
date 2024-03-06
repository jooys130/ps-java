package SWEA;


import java.io.*;
import java.util.*;

public class Solution_2814_D3_최장경로 {
    static int N, M;
    static boolean[][] map;
    static boolean[] visited;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new boolean[N+1][N+1];
            visited = new boolean[N+1];
            ans = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                map[start][end] = true;
                map[end][start] = true;
            }

            for (int i = 1; i <= N; i++) {
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    public static void dfs(int n, int cnt) {
        if (cnt > ans) ans = cnt;
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && map[n][i]) {
                visited[i] = true;
                dfs(i, cnt+1);
                visited[i] = false;
            }
        }
    }
}