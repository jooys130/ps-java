package BOJ;

import java.io.*;
import java.util.*;

public class Main_2116_G5_주사위쌓기 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int ans;
    static int[] dy = {5, 3, 4, 1, 2, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][6];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 첫번째 주사위 선택 (아랫면 기준)
        for (int i = 0; i < 6; i++) {
            visited = new boolean[N][6];
            dfs(0, map[0][i]);
        }
        System.out.println(ans);
    }
    public static void dfs(int x, int value) {
        if (x == N) {
            ans = Math.max(ans, calc());
            return;
        }

        // 아랫면 인덱스 구하기
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            if (map[x][i] == value) {
                idx = i;
                break;
            }
        }
        visited[x][idx] = true; // 아랫면
        visited[x][dy[idx]] = true; // 윗면
        dfs(x+1, map[x][dy[idx]]);
    }
    public static int calc() {
        int sum = 0;
        // 각 주사위 별로 false인 것 중 max 값 더하기
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < 6; j++) {
                if (!visited[i][j] && map[i][j] > max) max = map[i][j];
            }
            sum += max;
        }
        return sum;
    }
}
