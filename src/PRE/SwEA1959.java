package PRE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SwEA1959 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] A = new int[N];
            int[] B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            int answer = Integer.MIN_VALUE;
            for (int cnt = 0; cnt <= Math.abs(N - M); cnt++) {
                int tmp = 0;
                if (N < M) {
                    for (int i = 0; i < N; i++) {
                        tmp += (A[i] * B[i + cnt]);
                    }
                } else {
                    for (int i = 0; i < M; i++) {
                        tmp += (A[i + cnt] * B[i]);
                    }
                }
                answer = Math.max(answer, tmp);
            }
            System.out.println("#" + tc + " " + answer);


        }
    }

}
