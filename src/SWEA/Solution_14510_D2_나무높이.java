package SWEA;

import java.io.*;
import java.util.*;

public class Solution_14510_D2_나무높이 {
    // greedy
    static int[] trees;
    static int N; // treesCnt
    static int minDay;
    static int maxH;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N =  Integer.parseInt(br.readLine());
            trees = new int[N];
            maxH = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, trees[i]);
            }

            solve();

            sb.append("#").append(tc).append(" ").append(minDay).append("\n");
        }
        System.out.println(sb);
    }
    public static void solve() {
        // 물 주어야하는 높이 중 짝수 홀수 개수 계산
        int odd = 0; int even = 0;
        for (int i = 0; i < N; i++) {
            int diff = maxH - trees[i];
            odd += diff % 2;
            even += diff / 2;
        }

        /*
            odd와 even 1만큼 차이날 때까지(even - odd가 1보다 크면 공식 성립 x)
            짝수 날 한 번 = 홀수 날 "두 번"
         */
        while (even - odd > 1) {
            even--;
            odd += 2;
        }

        if (odd > even) minDay = 2 * odd -1;
        else if (odd < even) minDay = 2 * even;
        else minDay = odd + even;

    }
}
