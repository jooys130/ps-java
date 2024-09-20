package BOJ;

import java.io.*;
import java.util.*;

public class Main_2470_G5_두용액 {
    static int N;
    static long[] arr;
    static long pairA, pairB; // a와 b의 합이 0과 가장 가까운 경우
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long toZero = Long.MAX_VALUE;
        int start = 0; int end = N-1;
        while (start < end) {
            long tmp = arr[start] + arr[end];
            if (Math.abs(tmp) < toZero) {
                toZero = Math.abs(tmp);
                pairA = arr[start];
                pairB = arr[end];
            }
            if (tmp < 0) {
                start++;
            } else if (tmp > 0) {
                end--;
            } else {
                break;
            }
        }
        System.out.println(pairA + " " + pairB);
    }
}
