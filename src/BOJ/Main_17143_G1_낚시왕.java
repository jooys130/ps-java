package BOJ;

import java.io.*;
import java.util.*;

public class Main_17143_G1_낚시왕 {
    static int R, C, M;
    static int[][] map;
    static class Shark {
        int x; int y; int s; int dir; int size; boolean isLive;
        Shark(int x, int y, int s, int dir, int size, boolean isLive) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.dir = dir;
            this.size = size;
            this.isLive = isLive;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", s=" + s +
                    ", dir=" + dir +
                    ", size=" + size +
                    ", isLive=" + isLive +
                    '}';
        }
    }
    // 상 하 우 좌
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Shark[] sharks;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (M == 0) {
            System.out.println(0);
            return;
        }
        map = new int[R+1][C+1];
        sharks = new Shark[M+1];
        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(x, y, s, dir-1, size, true);
            map[x][y] = i;
        }

        for (int col = 1; col <= C; col++) {
            eat(col);
            move();
        }

        System.out.println(ans);
    }
    public static void eat(int col) {
        for (int i = 1; i <= R; i++) {
            if (map[i][col] > 0) {
                int idx = map[i][col];
                sharks[idx].isLive = false;
                ans += sharks[idx].size;
                break;
            }
        }
    }
    public static void move() {
        int[][] newMap = new int[R+1][C+1];
        for (int i = 1; i <= M; i++) {
            Shark s = sharks[i];
            if (!s.isLive) continue;
            // 이동거리 줄이기
            int dis = s.s;
            if (s.dir >= 2) {
                dis %= (C-1) * 2;
            } else {
                dis %= (R-1) * 2;
            }
            for (int j = 0; j < dis; j++) {
                int nx = s.x + dx[s.dir];
                int ny = s.y + dy[s.dir];
                // System.out.println("before " + s.dir + " (" + nx + " " + ny);
                if (nx < 1 || nx > R || ny < 1 || ny > C) {
                    nx -= dx[s.dir] * 2;
                    ny -= dy[s.dir] * 2;
                    s.dir = s.dir % 2 == 1 ? s.dir-1 : s.dir+1;
                    // System.out.println("after " + s.dir + " (" + nx + " " + ny);
                }
                s.x = nx; s.y = ny;
            }

            // 겹치면 큰 거로
            int idx = newMap[s.x][s.y];
            if (idx == 0) newMap[s.x][s.y] = i;
            else {
                if (sharks[idx].size < s.size) {
                    newMap[s.x][s.y] = i;
                    sharks[idx].isLive = false;
                } else {
                    s.isLive = false;
                }
            }
        }
        for (int i = 1; i <= R; i++) {
            map[i] = Arrays.copyOf(newMap[i], C+1);
        }
    }
}