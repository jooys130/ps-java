package preLearning;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SwEA1868 {

    static int N;
    static char[][] map;
    static int[][] count;
    static int answer;
    static int[] dx = {0, 0, -1, -1, -1, 1, 1, 1};
    static int[] dy = {-1, 1, 0, 1, -1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            count = new int[N][N];
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = input.charAt(j);
                }
            }

            // 지뢰 개수 세기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') {
                        int c = 0;
                        // 8방 탐색
                        for (int d = 0; d < 8; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                                continue;
                            }
                            if (map[nx][ny] == '*') {
                                c++;
                            }
                        }
                        count[i][j] = c;
                    }
                }
            }

            // 지뢰 없는 곳부터 방문 처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (count[i][j] == 0 && map[i][j] != '*') {
                        bfs(i, j);
                        answer++;
                    }
                }
            }

            // 눌리지 않은 곳 방문
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (count[i][j] > 0 && map[i][j] != '*') {
                        answer++;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        count[x][y] = -1; // 방문 처리

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 8; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                // 범위를 벗어나거나 이미 눌렀거나 지뢰인 경우
                if (nx < 0 || nx >= N || ny < 0 || ny >= N ||
                    count[nx][ny] == -1 || map[x][y] == '*') {
                    continue;
                }
                // 또 0인게 있다면 추가
                if (count[nx][ny] == 0) {
                    q.offer(new Point(nx, ny));
                }
                count[nx][ny] = -1;
            }
        }
    }

}
