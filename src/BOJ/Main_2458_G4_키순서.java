package BOJ;

import java.io.*;
import java.util.*;

public class Main_2458_G4_키순서 {
    static int N, M;
    static int[][] small;
    static int[][] big;
    static int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        small = new int[N+1][N+1];
        big = new int[N+1][N+1];

        init(small);
        init(big);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            small[a][b] = 1;
            big[b][a] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    big[i][j] = Math.min(big[i][j], big[i][k] + big[k][j]);
                    small[i][j] = Math.min(small[i][j], small[i][k] + small[k][j]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            boolean flag = true;
            for (int j = 1; j <= N; j++) {
                if (big[i][j] == INF && small[i][j] == INF) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }

        System.out.println(ans);
    }
    public static void init(int[][] map) {
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (i == j) continue;
                map[i][j] = INF;
            }
        }
    }
}