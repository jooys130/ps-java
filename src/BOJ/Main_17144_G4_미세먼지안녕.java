package BOJ;

import java.io.*;
import java.util.*;

public class Main_17144_G4_미세먼지안녕 {
    static int R, C, T;
    static int[][] map;
    static int clean;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {-1, 0, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 공기청정기 위치 정보 저장
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                clean = i;
                break;
            }
        }

        while(T-- > 0) {
            spread();
            cleaning();
//            for (int i = 0; i < R; i++) {
//			    System.out.println(Arrays.toString(map[i]));
//		    }
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    ans += map[i][j];
                }
            }
        }
        System.out.println(ans);
    }

    public static void spread() {
        int[][] copy = new int[R][C];
        copy[clean][0] = -1;
        copy[clean+1][0] = -1;
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (map[x][y] > 0) {
                    int cnt = 0;
                    int amount = map[x][y] / 5;
                    if(amount == 0) {
                        copy[x][y] += map[x][y];
                        continue;
                    }
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) continue;
                        copy[nx][ny] += amount;
                        cnt++;
                    }
                    copy[x][y] += map[x][y] - amount * cnt;
                }
            }
        }
        map = copy;
    }

    public static void cleaning() {
        upside();
        downside();
    }
    public static void upside() {
        int rr = clean;
        // down
        for (int i = rr-1; i >= 1; i--) {
            map[i][0] = map[i-1][0];
        }
        // left
        for (int i = 0; i < C-1; i++) {
            map[0][i] = map[0][i+1];
        }
        // up
        for (int i = 0; i < rr; i++) {
            map[i][C-1] = map[i+1][C-1];
        }
        // right
        for (int i = C-2; i > 0; i--) {
            map[rr][i+1] = map[rr][i];
        }
        map[rr][1] = 0;
    }

    public static void downside() {
        int rr = clean + 1;
        // up
        for (int i = rr+1; i < R-1; i++) {
            map[i][0] = map[i+1][0];
        }
        // left
        for (int i = 0; i < C-1; i++) {
            map[R-1][i] = map[R-1][i+1];
        }
        // down
        for (int i = R-1; i > rr; i--) {
            map[i][C-1] = map[i-1][C-1];
        }
        // right
        for (int i = C-2; i > 0; i--) {
            map[rr][i+1] = map[rr][i];
        }
        map[rr][1] = 0;
    }

    public static class Main_17952_S3_과제는끝나지않아 {
        static Deque<int[]> stack;
        static int ans;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int C = Integer.parseInt(br.readLine());
            // 자료구조 stack 활용
            stack = new ArrayDeque<int[]>();
            for (int i = 0; i < C; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                if (cmd == 1) {
                    int size = Integer.parseInt(st.nextToken());
                    int time = Integer.parseInt(st.nextToken());
                    if (time == 1) {
                        ans += size;
                    } else {
                        stack.addLast(new int[] {size, time-1});
                    }
                } else {
                    // 이전 작업 수행
                    if (!stack.isEmpty()) {
                        int[] cur = stack.pollLast();
                        if (cur[1] == 1) {
                            ans += cur[0];
                        } else {
                            stack.addLast(new int[] {cur[0], cur[1] - 1});
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }
}