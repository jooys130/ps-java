package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1249_D4_보급로 {
    static int N;
    static int[][] map;
    static int[][] visited;
    static class Node implements Comparable<Node> {

        int x;
        int y;
        int dis;

        public Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] data = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(data[j]);
                }
            }

            go();
            sb.append(visited[N-1][N-1]).append('\n');
        }
        System.out.println(sb);
    }

    private static void go() {
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, map[0][0]));
        visited[0][0] = 0;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny] <= visited[cur.x][cur.y] + map[nx][ny]) continue;
                visited[nx][ny] = visited[cur.x][cur.y] + map[nx][ny];
                queue.add(new Node(nx, ny, visited[nx][ny]));
            }
        }
    }

}