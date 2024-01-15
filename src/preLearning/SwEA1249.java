package preLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {

    int x;
    int y;
    int dis;

    public Node(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}

public class SwEA1249 {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] data = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(data[j]);
                }
            }
            int ans = bfs();
            System.out.println("#" + tc + " " + ans);
        }

    }

    public static int bfs() {
        int[][] visited = new int[N][N]; // dp로 사용
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        Queue<Node> q = new LinkedList<>();
        visited[0][0] = 0;
        q.offer(new Node(0, 0, 0));

        int dis = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.x == N-1 && n.y == N-1) {
                dis = Math.min(dis, n.dis);
                continue; // 이어서 탐색
            }

            for (int d = 0; d < 4; d++) {
                int nx = n.x + dx[d];
                int ny = n.y + dy[d];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    // 지금까지 비용 + 앞으로 비용
                    int tmp = n.dis + map[nx][ny];
                    if (visited[nx][ny] > tmp) {
                        visited[nx][ny] = tmp;
                        q.add(new Node(nx, ny, tmp));
                    }
                }
            }
        }
        return dis;
    }
}
