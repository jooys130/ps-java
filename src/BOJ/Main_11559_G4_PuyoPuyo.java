package BOJ;

import java.io.*;
import java.util.*;

public class Main_11559_G4_PuyoPuyo {
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int round;
    static boolean pop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (true) {
            pop = false;
            visited = new boolean[12][6];
            // 4개 이상 상하좌우
            for (int i = 11; i >= 0 ; i--) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        check(i, j, map[i][j]);
                    }
                }
            }
            // 다 없애고 나서 다운!!
            down();
            if (!pop) break;
            round++;
        }
        System.out.println(round);
    }
    public static void down() {
        for (int i = 0; i < 6; i++) {
            Queue<Character> color = new ArrayDeque<>();
            for (int j = 11; j >= 0; j--) {
                if (map[j][i] != '.') {
                    color.add(map[j][i]);
                    map[j][i] = '.';
                }
            }
            int k = 11;
            while(!color.isEmpty()) {
                map[k--][i] = color.poll();
            }
        }
    }
    public static void check(int x, int y, char color) {
        List<int[]> willRemove = new ArrayList<>();

        Queue<int[]> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.add(new int[] {x, y});
        willRemove.add(new int[] {x, y});
        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6 || visited[nx][ny]) continue;
                if (map[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                    willRemove.add(new int[] {nx, ny});
                }
            }
        }
        if (willRemove.size() >= 4) {
            for (int i = 0; i < willRemove.size(); i++) {
                int[] pos = willRemove.get(i);
                map[pos[0]][pos[1]] = '.';
            }
            pop = true;
        }
    }
}
