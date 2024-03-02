package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1959_D2_두개의숫자열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] a = new int[N];
            int[] b = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;
            for (int i = 0; i < Math.abs(N - M) + 1; i++) {
                int tmp = 0;
                if (N > M) {
                    for (int j = 0; j < M; j++) {
                        tmp += a[j+i] * b[j];
                    }
                } else {
                    for (int j = 0; j < N; j++) {
                        tmp += a[j] * b[j+i];
                    }
                }
                ans = Math.max(ans, tmp);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

}
