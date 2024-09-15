package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17069_G4_파이프옮기기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[N+1][N+1][3];
        dp[1][2][0] = 1; // 가로로 시작
        for (int i = 1; i < N+1; i++) {
            for (int j = 3; j < N+1; j++) {
                if (map[i][j] == 1) continue;

                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];

                dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];

                // 여기도 빈칸이어야 한다
                if (map[i-1][j] == 1 || map[i][j-1] == 1) continue;
                dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
            }
        }

        long[] ans = dp[N][N];
        System.out.println(ans[0] + ans[1] + ans[2]);
    }
}
