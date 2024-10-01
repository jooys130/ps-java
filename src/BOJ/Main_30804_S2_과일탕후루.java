package BOJ;

import java.io.*;
import java.util.*;

public class Main_30804_S2_과일탕후루 {
    static int N;
    static int[] arr;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                ans = Math.max(ans, getLongestLength(i, j));
            }
        }
        System.out.println(ans);
    }
    public static int getLongestLength(int i, int j) {
        int cnt = 0;
        int tmp = 0;
        for (int k = 0; k < N; k++) {
            if (arr[k] == i || arr[k] == j) {
                tmp++;
            } else {
                cnt = Math.max(cnt, tmp);
                tmp = 0;
            }
        }
        cnt = Math.max(cnt, tmp);
        return cnt;
    }

}
