package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoJ1189 {

    static int R, C, K;
    static char[][] road;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        road = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                road[i][j] = input.charAt(j); // String => substring
            }
        }

        visited[R-1][0] = true;
        dfs(R - 1, 0, 1);

        System.out.println(answer);

    }

    private static void dfs(int x, int y, int dist) {
        if (x == 0 && y == C-1) {
            if (dist == K) {
                answer++;
            }
            return;
        }

        // (추가) 좌표에 돌아가지 않아도 K일 때 리턴
        if (dist == K) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }
            if (road[nx][ny] == 'T' || visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, dist + 1);
            visited[nx][ny] = false;
        }
    }
}
