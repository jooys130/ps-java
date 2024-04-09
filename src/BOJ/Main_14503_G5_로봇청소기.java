package BOJ;

import java.io.*;
import java.util.*;

public class Main_14503_G5_로봇청소기 {
    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static class Pos {
        int x; int y; int dir;
        public Pos(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        @Override
        public String toString() {
            return x + " " + y + " " + dir;
        }
    }
    static Pos robot;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // (r, c)는 (r+1, c+1) 값을 가리킨다
        robot = new Pos(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean();
        System.out.println(cnt);
    }
    public static void clean() {
        Queue<Pos> q = new ArrayDeque<>();
        q.add(robot);
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            // System.out.println(cur);

            if (map[cur.x][cur.y] == 0) {
                map[cur.x][cur.y] = -1; // 청소 표시
                cnt += 1;
            }

            // 청소되지 않은 빈칸이 하나라도 있는 경우 true
            boolean exist = false;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (map[nx][ny] == 1) continue;
                if (map[nx][ny] == 0) {
                    exist = true;
                    break;
                }
            }

            int dir = -1;
            // 전부 1인 경우
            if (!exist) {
                dir = (cur.dir + 2) % 4; // 후진
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (map[nx][ny] == 1) return;
                q.add(new Pos(nx, ny, cur.dir));
                // continue;
            } else {
                dir = (cur.dir - 1 + 4) % 4; // 반시계 -1
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (map[nx][ny] == 0) q.add(new Pos(nx, ny, dir));
                else q.add(new Pos(cur.x, cur.y, dir));

            }
        }
    }
}
