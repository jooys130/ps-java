package BOJ;

import java.util.*;
import java.io.*;

public class Main_2583_S1_영역구하기 {

    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static int N, M, K;
    static int[][] map;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            /* x축 대칭 */
            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt = 0;
                    dfs(i, j);
                    ans.add(cnt);
                }
            }
        }

        Collections.sort(ans);

        sb.append(ans.size()).append('\n');
        for (int i : ans) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        map[x][y] = 1;
        cnt++;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(map[nx][ny] == 0) {
                dfs(nx, ny);
            }
        }
    }

}