package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_모의_보호필름 {
    static int R, C, K;
    static int[][] map;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc < T+1; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[R][C];
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solve(0, 0);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
    public static void solve(int cnt, int idx) {
        if (testing()) {
            ans = Math.min(cnt, ans);
            return; // 더 이상 탐색하지 않아도 됨
        }
        if (cnt > ans) {
            return;
        }
        if (idx == R) {
            return;
        }

        // 지역 변수로 선언
        int[] tmp = Arrays.copyOf(map[idx], C);

        // 그대로
        solve(cnt, idx+1);
        // A 약 넣기
        input(idx, 0);
        solve(cnt+1, idx+1);
        // B 약 넣기
        input(idx, 1);
        solve(cnt+1, idx+1);

        // 되돌리기
        map[idx] = Arrays.copyOf(tmp, C);

    }
    public static void input(int idx, int value) {
        for (int i = 0; i < C; i++) {
            map[idx][i] = value;
        }
    }
    public static boolean testing() {
        for (int c = 0; c < C; c++) {
            boolean flag = false;
            // 1로 초기화
            int cnt = 1;
            for (int i = 1; i < R; i++) {
                if (map[i][c] == map[i-1][c]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                if (cnt >= K) { // 왜 3이니 ..
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}