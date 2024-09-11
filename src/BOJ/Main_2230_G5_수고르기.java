package BOJ;

import java.io.*;
import java.util.*;

public class Main_2230_G5_수고르기 {
    static int N;
    static long M;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long ans = Long.MAX_VALUE;
        int start = 0; int end = 0;
        while (start < N && end < N) {
            long tmp = arr[end] - arr[start];
            if (tmp >= M) {
                ans = Math.min(tmp, ans);
                start++;
            } else {
                end++;
            }
        }
        System.out.println(ans);
    }
}
