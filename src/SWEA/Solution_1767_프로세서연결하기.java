package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기 {
    static int N;
    static int maxCore, minWire;
    static int[][] map;
    static List<int[]> cores;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 1; i < N-1; i++) {
                for (int j = 1; j < N-1; j++) {
                    if (map[i][j] == 1) cores.add(new int[] {i, j});
                }
            }

            maxCore = 0;
            minWire = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWire);
        }
    }

    public static void dfs(int idx, int coreCnt, int wireLen) {
        if (coreCnt + (cores.size() - idx) < maxCore) return;

        if (idx == cores.size()) {
            if (maxCore < coreCnt) {
                maxCore = coreCnt;
                minWire = wireLen;
            } else if (maxCore == coreCnt) {
                minWire = Math.min(wireLen, minWire);
            }
            return;
        }

        int x = cores.get(idx)[0];
        int y = cores.get(idx)[1];

        for (int i = 0; i < 4; i++) {
            boolean flag = false;
            int nx = x; int ny = y;
            int cnt = 0;
            while (true) {
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    flag = true;
                    break;
                }
                if (map[nx][ny] == 1) break;
                cnt++;
            }
            if (flag) {
                for (int j = 1; j <= cnt; j++) {
                    map[x + dx[i] * j][y + dy[i] * j] = 1;
                }
                dfs(idx+1, coreCnt+ 1, wireLen+cnt);
                for (int j = 1; j <= cnt; j++) {
                    map[x + dx[i] * j][y + dy[i] * j] = 0;
                }
            } else {
                dfs(idx+1, coreCnt, wireLen);
            }
        }
    }
}

