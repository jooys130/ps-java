package SSWAT;

import java.io.*;
import java.util.*;

public class Main_14500_G4_테트로미노 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int ans;
    static int MAX = 0; // 가지치기 위해 필요

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > MAX) {
                    MAX = map[i][j];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(ans);
    }

    public static void dfs(int x, int y, int cnt, int sum) {
        // 가지치기
        if (sum + (4 - cnt) * MAX <= ans) {
            return;
        }
        // 임계 조건
        if (cnt == 4) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                continue;
            }
            if (cnt == 2) {
                visited[nx][ny] = true;
                dfs(x, y, cnt + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}