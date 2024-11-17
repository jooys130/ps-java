package PRO;

import java.util.*;
class Solution_PCCP2_보물지도 {
    static int ans = -1;
    static int[][] map;
    static boolean[][][] visited;
    static Queue<Pos> q = new LinkedList<>();
    static class Pos {
        int x;
        int y;
        int dis;
        boolean jump;
        Pos(int x, int y, int dis, boolean jump) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.jump = jump;
        }
    }
    public int solution(int n, int m, int[][] hole) {
        map = new int[n+1][m+1];
        visited = new boolean[n+1][m+1][2];
        int[] dx = {0, 1, -1, 0};
        int[] dy = {-1, 0, 0, 1};
        for (int i = 0; i < hole.length; i++) {
            map[hole[i][0]][hole[i][1]] = -1; // 구멍
        }
        q.add(new Pos(1, 1, 0, false));
        visited[1][1][0] = true;
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.x == n && cur.y == m) {
                if (visited[cur.x][cur.y][1]) return cur.dis;
                return cur.dis - 1; // 구멍이 없어서 안 뛴 경우
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isOutOfRange(nx, ny, n+1, m+1)) continue;
                if (cur.jump && visited[nx][ny][1]) continue;
                if (!cur.jump && visited[nx][ny][0]) continue;
                if (map[nx][ny] == -1) {
                    if (cur.jump) continue; // 이미 뛰었을 때
                    nx += dx[i];
                    ny += dy[i];
                    if (isOutOfRange(nx, ny, n+1, m+1) || map[nx][ny] == -1) continue;
                    q.add(new Pos(nx, ny, cur.dis + 1, true));
                    visited[nx][ny][1] = true;
                } else {
                    if (cur.jump) {
                        q.add(new Pos(nx, ny, cur.dis + 1, true));
                        visited[nx][ny][1] = true;
                    }
                    else {
                        q.add(new Pos(nx, ny, cur.dis + 1, false));
                        visited[nx][ny][0] = true;
                    }
                }
            }
        }
        return ans;
    }
    public boolean isOutOfRange(int x, int y, int N, int M) {
        if (x <= 0 || x >= N || y <= 0 || y >= M) return true;
        return false;
    }
}