package PRE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SwEA12712 {

    static int N;
    static int M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int tmp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int plusCount = plusSpray(i, j);
                    int multiCount = multiSpray(i, j);
                    tmp = (plusCount > tmp) ? plusCount : tmp;
                    tmp = (multiCount > tmp) ? multiCount : tmp;
                }
            }
            System.out.println("#" + tc + " " + tmp);
        }
    }

    private static int multiSpray(int i, int j) {
        int[] dx = {-1, 1, -1, 1};
        int[] dy = {-1, -1, 1, 1};
        return getCount(dx, dy, i, j);
    }

    private static int plusSpray(int i, int j) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        return getCount(dx, dy, i, j);
    }

    private static int getCount(int[] dx, int[] dy, int i, int j) {
        int count = map[i][j];
        for (int d = 0; d < 4; d++) {
            for (int k = 1; k < M; k++) {
                int nx = i + dx[d] * k;
                int ny = j + dy[d] * k;
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    break;
                }
                count += map[nx][ny];
            }
        }
        return count;
    }
}
