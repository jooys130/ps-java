package HSAT;


import java.io.*;
import java.util.*;

public class Main_기출_로봇이지나간경로 {
    static int H, W;
    static char[][] map;
    static boolean[][] visited;
    // 동 남 서 북
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[] a = {'>', 'v', '<', '^'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int[] pos = findEnd();
        sb.append(pos[0] + 1).append(" ").append(pos[1] + 1).append("\n").append(a[pos[2]]).append("\n");

        List<Integer> dd = new ArrayList<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.add(pos);
        visited[pos[0]][pos[1]] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (outOfRange(nx, ny)) continue;
                if (!visited[nx][ny] && map[nx][ny] == '#') {
                    q.add(new int[] {nx, ny, i});
                    visited[nx][ny] = true;
                    dd.add(i);
                }

            }
        }

        int cur = pos[2];
        int cnt = 1;
        for (Integer next : dd) {
            if (cur == next) {
                cnt += 1;
                cur = next;
                if (cnt % 2 == 0) {
                    sb.append('A');
                    cnt = 0;
                }
            } else {
                if ((next - cur + 4) % 4 == 1) {
                    sb.append('R');
                } else {
                    sb.append('L');
                }
                cur = next;
                cnt = 1;
            }
        }
        System.out.println(sb);
    }
    public static int[] findEnd() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '#') {
                    int cnt = 0;
                    int dir = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (outOfRange(nx, ny)) continue;
                        if (map[nx][ny] == '#') {
                            cnt++;
                            dir = d;
                        }
                    }
                    if(cnt == 1) return new int[] {i, j, dir};
                }
            }
        }
        return new int[] {};
    }
    public static boolean outOfRange(int nx, int ny) {
        if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
            return true;
        }
        return false;
    }
}
