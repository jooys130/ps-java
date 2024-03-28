package BOJ;

import java.io.*;

public class Main_2239_G4_스도쿠 {
    static int N = 9;
    static int[][] map;
    public static void dfs(int x, int y) {
        if (y == N) {
            dfs(x+1, 0);
            return;
        }
        if (x == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }
        if (map[x][y] == 0) {
            for (int i = 1; i <= N; i++) {
                if (!promising(x, y, i)) continue;
                map[x][y] = i;
                dfs(x, y+1);
            }
            map[x][y] = 0;
            return;
        }
        dfs(x, y+1);
    }

    public static boolean promising(int x, int y, int value) {
        for (int i = 0; i < N; i++) {
            if (map[x][i] == value) return false;
        }
        for (int i = 0; i < N; i++) {
            if (map[i][y] == value) return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[(x/3)*3+i][(y/3)*3+j] == value) return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }
        dfs(0, 0);
    }
}
