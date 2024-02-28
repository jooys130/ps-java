package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1077_G5_배낭채우기1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] w = new int[N+1];
        int[] p = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // 각각의 보석은 무제한
        int[] dp = new int[W+1];
        for (int j = 1; j <= N; j++) {
            for (int i = w[j]; i <= W; i++) {
                dp[i] = Math.max(dp[i], dp[i-w[j]] + p[j]);
            }
        }
        System.out.println(dp[W]);
    }
}
