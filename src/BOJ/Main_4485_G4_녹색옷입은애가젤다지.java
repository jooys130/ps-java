package BOJ;

import java.io.*;
import java.util.*;

public class Main_4485_G4_녹색옷입은애가젤다지 {
    static int N;
    static int map[][];
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {-1, 0, 0, 1};
    static class Node implements Comparable<Node> {
        int x; int y;
        int cost;
        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int num = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            sb.append("Problem ").append(num++).append(":").append(" ");
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
            }

            Queue<Node> queue = new PriorityQueue<>();
            queue.add(new Node(0, 0, 0));
            visited[0][0] = map[0][0];
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N ) continue;
                    int next = visited[cur.x][cur.y] + map[nx][ny];
                    if (visited[nx][ny] > next) {
                        visited[nx][ny] = next;
                        queue.add(new Node(nx, ny, visited[nx][ny]));
                    }
                }
            }
            sb.append(visited[N-1][N-1]).append("\n");
        }
        System.out.println(sb);
    }
}
