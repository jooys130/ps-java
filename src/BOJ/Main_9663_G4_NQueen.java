package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_9663_G4_NQueen {
    static int N;
    static int[] map; // index : 행 / value : 열
    static int ans;
    public static void dfs(int x) {
        if (x == N) {
             // System.out.println(Arrays.toString(map));
            ans++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!promising(x, i)) continue;
            map[x] = i;
            dfs(x+1);
        }
    }
    public static boolean promising(int x, int value) {
        // 동일 열
        for (int i = 0; i < x; i++) {
            if (map[i] == value) return false;
        }
        for (int i = 0; i < x; i++) {
            if (Math.abs(x - i) == Math.abs(value - map[i])) return false;
            /*
            // 대각선 왼쪽 위
            if (map[i] - i == value - x) return false;
            // 대각선 오른쪽 위
            if (map[i] + i == value + x) return false;
             */
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N];
        dfs(0);
        System.out.println(ans);
    }
}
