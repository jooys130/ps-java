package BOJ;

import java.io.*;
import java.util.*;

public class Main_1938_G2_통나무옮기기 {
    static int N;
    static int[][] map;
    static boolean[][][] visited; // 가로인 경우, 세로인 경우 나눠서 방문 처리
    static class Wood {
        // Queue에서 관리할 통나무 객체: 중간의 위치, 방향, 굴린 횟수
        int x, y, dir, dis;
        Wood(int x, int y, int dir, int dis) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dis = dis;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")" + dir + ", " + dis;
        }
    }
    static List<int[]> B; // 시작 통나무 위치
    static List<int[]> E; // 끝 통나무 위치
    static int EShape = 0;
    // 상 하 좌 우 회전용
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = 0;
        N = Integer.parseInt(br.readLine().strip());
        B = new ArrayList<>();
        E = new ArrayList<>();
        map = new int[N][N];
        visited = new boolean[N][N][2];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                char n = input.charAt(j);
                if (n == 'B') {
                    B.add(new int[] {i, j});
                } else if (n == 'E') {
                    E.add(new int[] {i, j});
                } else if (n == '1') map[i][j] = 1;
            }
        }
        // 세로인 경우
        if (E.get(0)[1] == E.get(1)[1]) EShape = 1;
        System.out.println(bfs());
    }
    public static int bfs() {
        Queue<Wood> q = new ArrayDeque<>();
        int[] mid = B.get(1);

        int dir = 0; // 가로 방향
        if (B.get(0)[1] == mid[1]) dir = 1; // 세로 방향

        q.add(new Wood(mid[0], mid[1], dir, 0));
        visited[mid[0]][mid[1]][dir] = true;

        while (!q.isEmpty()) {
            Wood t = q.poll();
            // System.out.println(t);
            int curX = t.x;
            int curY = t.y;
            int curDir = t.dir;
            int curDis = t.dis;

            if (curX == E.get(1)[0] && curY == E.get(1)[1] && curDir == EShape) {
                return curDis;
            }

            // 상하좌우
            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                if (outOfRange(nx, ny, curDir)) continue;
                if (!canMove(nx, ny, curDir, i)) continue;

                visited[nx][ny][curDir] = true;
                q.add(new Wood(nx, ny, curDir, curDis+1));
            }
            // 회전
            if (visited[curX][curY][(curDir+1)%2]) continue;
            if (!canRotate(curX, curY)) continue;
            visited[curX][curY][(dir+1)%2] = true;
            q.add(new Wood(curX, curY, (curDir+1)%2, curDis + 1));
        }
        return 0;
    }
    public static boolean canMove(int nx, int ny, int curDir, int i) {
        if (curDir == 0) {
            if (i < 2) { // 상하 => 양쪽 확인
                if (out(ny-1) || out(ny+1) || map[nx][ny-1] == 1 || map[nx][ny+1] == 1) return false;
            } else { // 좌우 => 밀리는 곳만 확인
                int tmpY = ny + dy[i];
                if (out(tmpY) || map[nx][tmpY] == 1) return false;
            }
        } else {
            if (i < 2) { // 상하
                int tmpX = nx + dx[i];
                if (out(tmpX) || map[tmpX][ny] == 1) return false;
            } else { // 좌우
                if (out(nx-1) || out(nx+1) || map[nx-1][ny] == 1 || map[nx+1][ny] == 1) return false;
            }
        }
        return true;
    }
    public static boolean canRotate(int x, int y) {
//        if (out(curX-1) || out(curX+1) || out(curY-1) || out(curY+1)) return false;
//        if (map[curX-1][curY] == 1 || map[curX+1][curY] == 1 || map[curX][curY-1] == 1
//                || map[curX][curY+1] == 1 || map[curX-1][curY-1] == 1 || map[curX+1][curY+1] == 1) return false;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
                if (out(i) || out(j)) return false;
                if (map[i][j] == 1) return false;
            }
        }
        return true;
    }
    public static boolean outOfRange(int x, int y, int dir) {
        return x < 0 || x >= N || y < 0 || y >= N || visited[x][y][dir] || map[x][y] == 1;
    }
    public static boolean out(int k) {
        return k < 0 || k >= N;
    }
}

