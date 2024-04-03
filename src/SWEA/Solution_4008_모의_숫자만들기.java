package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4008_모의_숫자만들기 {
    static int N;
    static int[] operCnt;
    static int[] nums;
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int maxValue;
    static int minValue;
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        operCnt = new int[4];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < 4; i++) {
            operCnt[i] = Integer.parseInt(st.nextToken());
        }
        nums = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }
    public static int calculate(int a, int b, int op) {
        // + - * /
        if (op == 0) return a + b;
        else if (op == 1) {
            return a - b;
        } else if (op == 2) {
            return a * b;
        }
        return a/b;
    }
    public static void permu(int value, int idx) {
        if (idx == N-1) {
            minValue = Math.min(value, minValue);
            maxValue = Math.max(value, maxValue);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operCnt[i] == 0) {
                continue;
            }
            operCnt[i]--;
            permu(calculate(value, nums[idx+1], i), idx+1);
            operCnt[i]++;
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // 초기화
            minValue = Integer.MAX_VALUE;
            maxValue = Integer.MIN_VALUE;
            input();
            permu(nums[0], 0); // 수식 만들기
            sb.append("#").append(tc).append(" ")
                    .append(maxValue - minValue).append("\n");
        }
        System.out.println(sb);
    }
}
