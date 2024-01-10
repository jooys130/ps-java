package preLearning;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SwEA5215 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[] score = new int[N+1];
            int[] kcal = new int[N+1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                kcal[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N+1][L+1];
            for (int item = 0; item < N+1; item++) {
                // 아무것도 선택하지 않았을 때
                dp[item][0] = 0;
            }
            for (int k = 0; k < L+1; k++) {
                // 칼로리 섭취하지 않은 경우
                dp[0][k] = 0;
            }

            for (int i = 1; i < N+1; i++) {
                for (int k = 1; k < L+1; k++) {
                    int ci = kcal[i];
                    int si = score[i];
                    // item i의 칼로리가 현재 섭취 칼로리보다 큰 경우
                    if (ci > k) {
                        // i-1까지만 담는다
                        dp[i][k] = dp[i-1][k];
                    } else {
                        dp[i][k] = Math.max(dp[i-1][k], si + dp[i-1][k - ci]);
                    }
                }
            }

            System.out.println("#" + tc + " " + dp[N][L]);

        }
    }

}