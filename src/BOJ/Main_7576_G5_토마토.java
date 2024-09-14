package BOJ;

import java.util.*;
import java.io.*;

public class Main_7576_G5_토마토 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int zeroCnt;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
                else if (map[i][j] == 0) zeroCnt++;
            }
        }
        if (zeroCnt == 0) System.out.println(0);
        else {
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};
            while(!q.isEmpty()) {
                int[] arr = q.poll();
                for (int i = 0; i < 4; i++) {
                    int x = arr[0] + dx[i];
                    int y = arr[1] + dy[i];
                    if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == -1 || visited[x][y]) continue;
                    q.add(new int[]{x, y, arr[2] + 1});
                    visited[x][y] = true;
                    zeroCnt--;
                    ans = Math.max(arr[2] + 1, ans);
                }
            }
            System.out.println(zeroCnt > 0 ? -1 : ans);
        }
    }
}