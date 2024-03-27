package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_2239_G4_스도쿠 {
    static int N = 9;
    static int[][] map;
    static int zeroCnt;
    static Queue<int[]> zero;
    static List<int[]>[] avail;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
//        zero = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 0) {
                    zeroCnt++;
//                    zero.add(new int[] {i, j});
                }
            }
        }
        avail = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            avail[i] = new ArrayList<>();
        }

        // 1 ~ 9까지
        for (int i = 1; i < N+1; i++) {
            search(i);
        }
        for (int i = 0; i < N; i++) {
            System.out.println(avail[i]);
        }
        solve();
    }
    public static void solve() {

    }
    public static void search(int num) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (hori(i, num) && ver(j, num) && sq(i, j, num)) {
                    avail[num].add(new int[] {i, j});
                }
            }
        }
    }

    public static boolean hori(int x, int value) {
        for (int i = 0; i < N; i++) {
            if (map[x][i] == value) return false;
        }
        return true;
    }

    public static boolean ver(int y, int value) {
        for (int i = 0; i < N; i++) {
            if (map[i][y] == value) return false;
        }
        return true;
    }

    public static boolean sq(int x, int y, int value) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[x/3+i][y/3+j] == value) return false;
            }
        }
        return true;
    }
}
