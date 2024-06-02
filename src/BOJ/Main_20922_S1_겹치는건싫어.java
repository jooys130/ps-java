package BOJ;

import java.util.*;
import java.io.*;

public class Main_20922_S1_겹치는건싫어 {
    // K개 이하로 포함된 최장 연속 부분 수열의 길이를 구하여라
    static int N, K;
    static int[] a;
    /*
        개수를 어떻게 기억하지? -> count 배열
        위치를 어떻게 기억하지? -> 투 포인터
     */
    static int[] count;
    static int start, end;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N];
        count = new int[100_001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        // O(N)
        while (start < N) {
            int num = a[start];
            if (count[num] < K) {
                count[num]++;
                start++;
            } else {
                count[a[end]]--;
                end++;
            }
            answer = Math.max(answer, start - end);
            // System.out.println(start + " " + end + " " + answer);
            // System.out.println(num + " " + count[num]);
        }
        System.out.println(answer);
    }
}
