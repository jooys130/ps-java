package BOJ;

import java.io.*;
import java.util.*;

public class Main_24954_S1_물약구매 {

    static int N;
    static int[] c;
    static boolean[] isSelected;
    static List<int[]>[] sales;
    static int ans = Integer.MAX_VALUE;

    public static void solve() {
        isSelected = new boolean[N];
        permu(0, 0);
        System.out.println(ans);
    }

    public static void permu(int cnt, int sum) {
        if (ans < sum) return;
        if (cnt == N) {
            ans = Math.min(ans, sum);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;

            // i번째 구매 시 가격 할인
            for (int[] pair: sales[i]) {
                c[pair[0]] -= pair[1];
            }
            // i번째 구매
            permu(cnt + 1, sum + Math.max(1, c[i]));
            isSelected[i] = false;
            for (int[] pair: sales[i]) {
                c[pair[0]] += pair[1];
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        sales = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            sales[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(br.readLine());
            for (int j = 0; j < p; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());
                sales[i].add(new int[]{a, d});
            }
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
