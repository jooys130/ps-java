package preLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Player {

    int x;
    int y;
    int time;

    public Player(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class SwEA4193 {

    // dfs 시간초과 => bfs

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int startX;
    static int startY;
    static int endX;
    static int endY;
    static int answer;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            answer = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int k = Integer.parseInt(stringTokenizer.nextToken());
                    map[i][j] = k;
                    if (k == 1) {
                        visited[i][j] = true;
                    }
                }
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            if (startX == endX && startY == endY) {
                System.out.println("#" + tc + " " + 0);
                continue;
            }
            System.out.println("#" + tc + " " + (bfs() ? answer : -1));
        }
    }

    private static boolean bfs() {
        Queue<Player> queue = new LinkedList<>();
        queue.offer(new Player(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Player player = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = player.x + dx[i];
                int ny = player.y + dy[i];
                if (0 > nx || nx >= N || 0 > ny || ny >= N) {
                    continue;
                }
                if (nx == endX && ny == endY) {
                    answer = player.time + 1;
                    return true;
                }
                if (map[nx][ny] == 1 || visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 2) {
                    if ((player.time - 2) % 3 != 0) {
                        // 소용돌이 안 풀리면 그냥 머무르는 경우 추가
                        // visited[player.x][player.y] = true;
                        queue.add(new Player(player.x, player.y, player.time + 1));
                        continue;
                    }
                }
                // 0인 경우 + 소용돌이 풀리는 경우
                visited[nx][ny] = true;
                queue.add(new Player(nx, ny, player.time + 1));
            }
        }
        return false;
    }

}
