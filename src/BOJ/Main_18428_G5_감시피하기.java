package BOJ;

import java.io.*;
import java.util.*;

public class Main_18428_G5_감시피하기 {
    static int N;
    static char[][] map;
    static List<int[]> teachers;
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teachers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine().replace(" ", "");
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'T') teachers.add(new int[]{i, j});
            }
        }
        dfs(0, 0, 0);
        System.out.println("NO");
    }
    public static void dfs(int x, int y, int cnt) {
        if (cnt == 3) {
            if (check()) flag = true;
            if (flag) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }
        if (y >= N) {
            x++; y = 0;
        }
        if (x == N) return;
        if (map[x][y] == 'X') {
            map[x][y] = 'O';
            dfs(x, y + 1, cnt + 1);
            map[x][y] = 'X';
        }
        dfs(x, y+1, cnt);
    }
    public static boolean check() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] pos : teachers) {
            int x = pos[0];
            int y = pos[1];
            for (int[] dir : directions) {
                int nx = x;
                int ny = y;
                while (true) {
                    nx += dir[0];
                    ny += dir[1];
                    if (nx >= N || nx < 0 || ny >= N || ny < 0) break;
                    if (map[nx][ny] == 'O') break;
                    if (map[nx][ny] == 'S') return false;
                }
            }
        }
        return true;
    }
}
