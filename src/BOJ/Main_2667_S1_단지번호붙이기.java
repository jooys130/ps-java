package BOJ;

import java.io.*;
import java.util.*;

public class Main_2667_S1_단지번호붙이기 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {-1, 0, 0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        List<Integer> houses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    houses.add(bfs(i, j));
                }
            }
        }
        System.out.println(houses.size());
        Collections.sort(houses);
        for (int cnt: houses) {
            System.out.println(cnt);
        }
    }
    public static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
                if (map[nx][ny] == 1) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
