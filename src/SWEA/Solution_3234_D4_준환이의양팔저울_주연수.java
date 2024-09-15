package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3234_D4_준환이의양팔저울_주연수 {

    static int N;
    static int[] W;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            ans = 0;
            sb.append("#" + tc + " ");
            N = Integer.parseInt(br.readLine());
            W = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                W[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(W);

            do {
                dfs(0, 0, 0);
            } while (np(W));

            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

    private static boolean np(int[] p) {
        final int N = p.length;

        int i = N - 1;
        while (i > 0 && p[i - 1] >= p[i]) {
            --i;
        }

        if (i == 0) {
            return false;
        }

        int j = N - 1;
        while (p[i - 1] >= p[j]) {
            --j;
        }

        swap(p, i - 1, j);

        int k = N - 1;
        while (i < k) {
            swap(p, i++, k--);
        }

        return true;
    }

    public static void swap(int[] p, int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    public static void dfs(int index, int right, int left) {
        if (left < right) return;

        if (index == N) {
            ans++;
            return;
        }

        dfs(index + 1, right + W[index], left);
        dfs(index + 1, right, left + W[index]);
    }
}
