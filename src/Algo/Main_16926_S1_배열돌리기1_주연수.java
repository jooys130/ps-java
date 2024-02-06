package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_S1_배열돌리기1_주연수 {

    /*
     * 수행 시간:	796ms
     * 메모리:	38,840KB
     *
     *  1 ≤ R ≤ 1,000
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

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < Math.min(M, N)/2; j++) {
                rotate(j);
            }
        }
        print();
    }

    private static void rotate(int r) {
        int tmp = S[r][r];
        for (int i = r; i < M-r-1; i++) {
            S[r][i] = S[r][i+1];
            // System.out.println("1 (" + r + " ," + i + ")");
        }
        for (int i = r; i < N-r-1; i++) {
            S[i][M-r-1] = S[i+1][M-r-1];
            // System.out.println("2 (" + i + " ," + (M-r-1) + ")");
        }
        for (int i = M-r-1; i > r; i--) {
            S[N-r-1][i] = S[N-r-1][i-1];
            // System.out.println("3 (" + (N-r-1) + " ," + i + ")");
        }
        for (int i = N-r-1; i > r+1; i--) {
            S[i][r] = S[i-1][r];
            // System.out.println("4 (" + i + " ," + r + ")");
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
