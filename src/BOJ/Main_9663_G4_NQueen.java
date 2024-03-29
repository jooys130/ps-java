package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663_G4_NQueen {
    static int N;
    static boolean[][] map;
    static int ans;
    public static void dfs(int x) {
        if (x == N) {
//            for (int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println();
            ans++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!promising(x, i)) continue;
            map[x][i] = true;
            dfs(x+1);
            map[x][i] = false;
        }
    }
    public static boolean promising(int x, int y) {
        for (int i = 0; i < N; i++) {
            if (map[x][i]) return false;
        }
        for (int i = 0; i < N; i++) {
            if(map[i][y]) return false;
        }
        // 대각선 위로만 !!
        for (int i = x-1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                // 오른쪽 위
                if (x + y == i + j && map[i][j]) return false;
                // 왼쪽 위
                if (x - y == (i - j) && map[i][j]) return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[0][i] = true;
            dfs(1);
            map[0][i] = false;
        }
        System.out.println(ans);
    }
}
