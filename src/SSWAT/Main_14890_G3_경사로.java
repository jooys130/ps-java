package SSWAT;

import java.util.*;
import java.io.*;

public class Main_14890_G3_경사로 {

    static int N, M;
    static int[][] map, map2;
    static int ans;
    static boolean[] visited;

    public static void solve(int[][] map) {
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            boolean flag = true;
            int same = 1; // up
            int cnt = 0; // down
            for (int j = 1; j < N; j++) {
                if (map[i][j - 1] == map[i][j] && !visited[j]) {
                    same++;
                    continue;
                }
                if (map[i][j - 1] == map[i][j] + 1) { // down
                    // M개 만큼 탐색
                    for (int k = j; k < Math.min(j + M, N); k++) {
                        if (map[i][j] == map[i][k]) {
                            cnt++;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (cnt < M) {
                        flag = false;
                        break;
                    }
                    // 경사로 설치
                    for (int k = 0; k < M; k++) {
                        visited[j + k] = true;
                    }
                    cnt = 0;
                    same = 0; // 경사로 설치할 때 사용했으니 초기화
                } else if (map[i][j - 1] == map[i][j] - 1) { // up
                    if (same < M) {
                        flag = false;
                        break;
                    }
                    same = 1;
                } else if (Math.abs(map[i][j - 1] - map[i][j]) >= 2) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans++;
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        map2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());
                map[i][j] = k;
                map2[j][i] = k;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve(map); // 행 단위
        solve(map2); // 열 단위
        System.out.println(ans);
    }

}

