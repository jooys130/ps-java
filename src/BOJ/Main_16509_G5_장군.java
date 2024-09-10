package BOJ;

import java.io.*;
import java.util.*;

public class Main_16509_G5_장군 {
    static int R1, C1, R2, C2;
    static int ans;
    static boolean[][] visited;
    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 좌상, 우상, 좌하, 우하
    static int[] ddx = {-1, -1, 1, 1};
    static int[] ddy = {-1, 1, -1, 1};
    static int[][] dirs = {
            {0, 1}, // 상
            {2, 3}, // 하
            {0, 2}, // 좌
            {1, 3}  // 우
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 상의 위치
        StringTokenizer st = new StringTokenizer(br.readLine());
        R1 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        // 왕의 위치
        st = new StringTokenizer(br.readLine());
        R2 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());
        // bfs
        visited = new boolean[10][9];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{R1, C1, 0});
        visited[R1][C1] = true;
        while (!q.isEmpty()) {
            int[] arr = q.poll();
            if (arr[0] == R2 && arr[1] == C2) {
                ans = arr[2];
                break;
            }
            for (int i = 0; i < 4; i++) {
                int x = arr[0] + dx[i];
                int y = arr[1] + dy[i];
                if (x < 0 || x >= 10 || y < 0 || y >= 9) continue;

                if (x == R2 && y == C2) continue;
                for (int d : dirs[i]) {
                    int nx = x + ddx[d] * 2;
                    int ny = y + ddy[d] * 2;
                    int blockX = x + ddx[d];
                    int blockY = y + ddy[d];
                    if (blockX == R2 && blockY == C2) continue;
                    if (nx >= 0 && nx < 10 && ny >= 0 && ny < 9) {
                        if (visited[nx][ny]) continue;
                        q.add(new int[]{nx, ny, arr[2] + 1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
