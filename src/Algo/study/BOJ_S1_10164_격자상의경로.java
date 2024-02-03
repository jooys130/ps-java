package Algo.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    (문제 설명)
    (0,0)에서 (N-1, M-1)까지 가는데 격자를 지나는 서로 다른 경로의 수는?
    1. 반드시 O 표시된 칸을 지나야 한다
    2. 오른쪽 또는 아래로 이동할 수 있다

    (나의 풀이) - 32점
    1. 경로 구할 때 기본 덧셈 (dp)를 사용
    2. 범위를 설정하는데 많은 시간 소요

    (개선 사항) - 32점
    1. 'O'가 있는 경우 두 영역을 각자 구하고 곱해주면 된다
    2. 사이즈를 하나 더 늘려서 (0,1) 또는 (1, 0)에 1을 넣어주면 1로 초기화하는 경우를 고려하지 않아도 된다

    (개선 사항) - 100점
    *** x와 y를 정확하게 계산하는 게 중요했던 문제다
    종이에 계산하고 코드 짰으면 덜 걸렸을듯

    (중복 순열??)

 */

public class BOJ_S1_10164_격자상의경로 {

    static int N; // 격자의 행 수
    static int M; // 격자의 열 수
    static int K; // O 표시된 칸의 번호
    static int[][] dp; // 격자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        dp[0][1] = 1; // 또는 dp[1][0]

        findRoute(N, M);
        if (K == 0) {
            System.out.println(dp[N][M]);
        } else {
            int x = K / M + (K % M > 0 ? 1 : 0);
            int y = (K % M == 0 ? M : K % M);
            System.out.println(dp[x][y] * dp[N - x + 1][M - y + 1]);
        }
    }

    private static void findRoute(int x, int y) {
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
    }
}
