package BOJ;

import java.util.*;
import java.io.*;

public class Main_21610_G5_마법사상어와비바라기 {
    static class Pos {
        int x; int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pos)) {
                return false;
            }
            Pos pos = (Pos) obj;
            return x == pos.x && y == pos.y;
        }
    }
    static List<Pos> clouds;
    static int[][] A;
    static boolean[][] visited;
    static int N, M;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clouds = new ArrayList<>();
        clouds.add(new Pos(N, 1));
        clouds.add(new Pos(N, 2));
        clouds.add(new Pos(N-1, 1));
        clouds.add(new Pos(N-1, 2));
        for (int i = 0; i < M; i++) {
            visited = new boolean[N+1][N+1];
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            moveCloudsAndUpdateA(dir, dis);
            magic();
            updateClouds();
        }
        int ans = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N + 1; j++) {
                ans += A[i][j];
            }
        }
        System.out.println(ans);
    }

    private static void updateClouds() {
        List<Pos> newClouds = new ArrayList<>();
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if(A[i][j] >= 2) {
                    // 안 먹힘 -> equals overriding & 더 오래 걸림
                    if (clouds.contains(new Pos(i, j))) continue;
//                    if (visited[i][j]) continue;
                    newClouds.add(new Pos(i, j));
                    A[i][j] -= 2;
                }
            }
        }
        clouds = newClouds;
    }

    private static void magic() {
        for (int i = 0; i < clouds.size(); i++) {
            int cnt = 0;
            for (int d = 2; d <= 8; d+=2) {
                int nx = clouds.get(i).x + dx[d];
                int ny = clouds.get(i).y + dy[d];
                if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
                if(A[nx][ny] == 0) continue;
                cnt++;
            }
            A[clouds.get(i).x][clouds.get(i).y] += cnt;
        }
    }

    private static void moveCloudsAndUpdateA(int dir, int dis) {
        // 경계 넘어가도 이어짐
        for (int i = 0; i < clouds.size(); i++) {
            int x = clouds.get(i).x;
            int y = clouds.get(i).y;
            if (dx[dir] != 0) {
                x = (x + dx[dir] * dis % N + N) % N;
            }
            if (dy[dir] != 0) {
                y = (y + dy[dir] * dis % N + N) % N;
            }
            if (x == 0) x = N;
            if (y == 0) y = N;
            clouds.get(i).x = x;
            clouds.get(i).y = y;
            A[x][y]++; // updateA
            visited[x][y] = true;
        }
    }
    private static void printA() {
        for (int j = 1; j < N+1; j++) {
            for (int k = 1; k < N + 1; k++) {
                System.out.print(A[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
