package BOJ;

import java.io.*;
import java.util.*;

public class Main_1920_S4_수찾기 {
    static int N, M;
    static int[] A;
    static int start, end, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        start = A[0]; end = A[N - 1];
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            bs(tmp);
            System.out.println(answer);
        }
    }
    private static void bs(int tmp) {
        answer = 0;
        start = 0; end = N-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == tmp) {
                answer = 1;
                return;
            } else if (A[mid] < tmp) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

}
