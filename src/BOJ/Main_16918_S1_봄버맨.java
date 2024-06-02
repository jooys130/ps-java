package BOJ;

import java.io.*;
import java.util.*;

public class Main_16918_S1_봄버맨 {
    static int R, C, N;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> bombs = new ArrayDeque<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                // (1) 처음 봄버맨이 일부 칸에 폭탄을 설치해 놓는다.
                if(map[i][j] == 'O') {
                    bombs.add(new int[] {i, j});
                }
            }
        }
        // (2) 첫 1초 동안 봄버맨은 아무것도 하지 않는다.
        N--;
        while(true) {
            if (N == 0) break;
            // (3) 1초 더 흐른 후에는 모든 칸은 폭탄을 가진다
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] != 'O') {
                        map[i][j] = 'O';
                    }
                }
            }
            if (--N == 0) break;
            // (4) 1초 더 흐른 후에는 (3) 전에 설치된 폭탄이 모두 폭발한다.
            while(!bombs.isEmpty()) {
                int[] pos = bombs.poll();
                map[pos[0]][pos[1]] = '.';
                for (int i = 0; i < 4; i++) {
                    int nx = pos[0] + dx[i];
                    int ny = pos[1] + dy[i];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if (map[nx][ny] == 'O') {
                        map[nx][ny] = '.';
                    }
                }
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'O') bombs.add(new int[] {i, j});
                }
            }
            if (--N == 0) break;
        }
        print();
    }
    public static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}