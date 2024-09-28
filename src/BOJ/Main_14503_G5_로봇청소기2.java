package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_G5_로봇청소기2 {
    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static int row, col;
    static int x, y, dir;
    static int washingCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 시뮬레이션
        while (true) {
            // 1. 현재 칸 청소
            if(map[x][y] == 0) {
                map[x][y] = -1;
                washingCnt++;
            }

            // 2. 주변 4개 칸 탐색 -> 0인 거 있는 경우
            boolean canGo = false;
            int nx, ny;
            for (int i = 0; i < 4; i++) {
                // 반시계 방향으로 회전 후 한 칸 전진
                dir = (dir + 3) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
                if (map[nx][ny] == 0) {
                    canGo = true;
                    x = nx; y = ny;
                    break;
                }
            }
            // 3. 0인 거 없는 경우
            if (!canGo) {
                // 방향 유지한 채로 한 칸 후진
                int d = (dir + 2) % 4;
                nx = x + dx[d];
                ny = y + dy[d];
                // 후진할 곳이 벽인 경우 작동 멈춤
                if (map[nx][ny] == 1) break;
                else {
                    x = nx; y = ny;
                }
            }
        }
        System.out.println(washingCnt);
    }
}
