package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12865_G5_평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] w = new int[N+1];
        int[] v = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        /*
            이차원 배열
            53776 KB	192 ms
         */
        int[][] dp = new int[N+1][K+1];
        for (int idx = 1; idx <= N; idx++) {
            for (int limit = 1; limit <= K; limit++) {
                if (limit >= w[idx]) {
                    dp[idx][limit] = Math.max(dp[idx-1][limit-w[idx]] + v[idx], dp[idx-1][limit]);
                } else {
                    dp[idx][limit] = dp[idx-1][limit];
                }
            }
        }
        System.out.println(dp[N][K]);

        /*
            일차원 배열
            14700 KB    156 ms
         */
        int[] arr = new int[K+1];
        for (int i = 1; i <= N; i++) {
            // 한 개씩 넣어야 해서 뒤에서부터 값 갱신
            for (int j = K; j >= w[i]; j--) {
                arr[j] = Math.max(arr[j], arr[j-w[i]] + v[i]);
            }
        }
        System.out.println(arr[K]);
    }
}