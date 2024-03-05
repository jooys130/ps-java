package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoJ14501 {

    static int N;
    static int[] T; // 상담 기간
    static int[] P; // 상담 금액
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            if (i + T[i] <= N) { // 상담이 가능한 경우
                // i번째 상담을 진행했을 때
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
            // i번째 상담을 진행하지 않는 경우
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        System.out.println(dp[N]);
    }
}

/*
다른 풀이 방법
    dfs
    dp - 거꾸로
 */
