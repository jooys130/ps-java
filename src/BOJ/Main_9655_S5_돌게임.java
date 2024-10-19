package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9655_S5_돌게임 {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // System.out.println(N%2 == 0 ? "CY" : "SK");

        /*
            다이나믹 프로그래밍 활용
            dp[i] : i개의 돌로 진행한 게임 횟수
         */
        int[] dp = new int[10001];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.min(dp[i-1] + 1, dp[i-3] + 1);
        }
        System.out.println(dp[N] % 2 == 0 ? "CY" : "SK");
    }
}
