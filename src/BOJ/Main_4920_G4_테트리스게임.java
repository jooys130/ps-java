package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4920_G4_테트리스게임 {
    static int N;
    static int[][] board;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while (true) {
            ans = Integer.MIN_VALUE;
            String input = br.readLine().trim();
            if (input.equals("0")) break;
            N = Integer.parseInt(input);
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solve();
            sb.append(tc).append(". ").append(ans).append("\n");
            tc++;
        }
        System.out.println(sb);
    }

    static int[][][] blocks = {
            // 1번
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            // 2번
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
            // 3번
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
            // 4번
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, -1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
            // 5번
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
    };

    public static void solve() {
        for (int c = 0; c < blocks.length; c++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int sum = calc(c, i, j);
                    ans = Math.max(ans, sum);
                }
            }
        }
    }
    public static int calc(int c, int x, int y) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + blocks[c][i][0];
            int ny = y + blocks[c][i][1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) return Integer.MIN_VALUE;
            sum += board[nx][ny];
        }
        return sum;
    }
}
