package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4193_D4_수영대회결승전 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] start;
    static int[] end;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};
    static int ans;
    static class Swim {
        int x;
        int y;
        int time;
        public Swim(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            start = new int[] {x, y};
            visited[x][y] = true;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            end = new int[] {x, y};

            ans = Integer.MAX_VALUE;
            Deque<Swim> q = new ArrayDeque<>();
            q.add(new Swim(start[0], start[1], 0));
            while(!q.isEmpty()) {
                Swim pos = q.poll();
                if (pos.x == end[0] && pos.y == end[1]) {
                    ans = Math.min(pos.time, ans);
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = pos.x + dx[i];
                    int ny = pos.y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 || visited[nx][ny]) {
                        continue;
                    }
                    if (map[nx][ny] == 2 && pos.time % 3 != 2) {
                        // 소용돌이가 있는 경우
                        q.add(new Swim(pos.x, pos.y, pos.time+1));
                        continue;
                    }
                    q.add(new Swim(nx, ny, pos.time+1));
                    visited[nx][ny] = true;
                }
            }

            ans = ans == Integer.MAX_VALUE ? -1: ans;
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
