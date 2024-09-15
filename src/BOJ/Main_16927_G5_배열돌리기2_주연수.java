package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16927_G5_배열돌리기2_주연수 {

    /*
     * 수행 시간: 752 ms
     * 메모리: 37,924 KB
     *
     *  1 ≤ R ≤ 10^9
     */

    static int N;
    static int M;
    static int R;
    static int[][] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        S = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 어차피 반복되니까 R을 mod 연산 처리한다

//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < Math.min(M, N)/2; j++) {
//                rotate(j);
//            }
//        }

        int n = N; // 회전할 행 길이
        int m = M; // 회전할 열 길이
        for (int i = 0; i < Math.min(N, M) / 2; i++) {

            int r = R % (2 * (n + m - 2)); // (n - 1 + m - 1) * 2

            for (int j = 0; j < r; j++) {
                rotate(i);
            }

            n -= 2;
            m -= 2;
        }
        print();
    }

    private static void rotate(int r) {
        int tmp = S[r][r];
        for (int i = r; i < M-r-1; i++) {
            S[r][i] = S[r][i+1];
        }
        for (int i = r; i < N-r-1; i++) {
            S[i][M-r-1] = S[i+1][M-r-1];
        }
        for (int i = M-r-1; i > r; i--) {
            S[N-r-1][i] = S[N-r-1][i-1];
        }
        for (int i = N-r-1; i > r+1; i--) {
            S[i][r] = S[i-1][r];
        }
        S[r+1][r] = tmp;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(S[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
