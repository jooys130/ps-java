package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5656_모의_벽돌깨기 {
    static int N, W, H;
    static int[][] map;
    static int[][] copyMap;
    static int[] numbers;
    static int ans;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T ; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            ans = W * H + 1;
            map = new int[H][W];
            copyMap = new int[H][W];
            numbers = new int[N];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solve(0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    public static void solve(int cnt) {
        // 중복순열
        if (cnt == N) {
            copyMap = copy(map);
            ans = Math.min(go(), ans);
            return;
        }
        for (int i = 0; i < W; i++) {
            numbers[cnt] = i;
            solve(cnt+1);
        }
    }
    public static int go(){
        for (int i = 0; i < N; i++) {
            Queue<int[]> q = new ArrayDeque<>();
            int[] pos = getTop(numbers[i]);
            if (pos[0] == -1) continue;
            q.add(pos);
            // pop
            while (!q.isEmpty()) {
                pos = q.poll();
                for (int s = 0; s < pos[2]; s++) {
                    for (int j = 0; j < 4; j++) {
                        int nx = pos[0] + dx[j] * s;
                        int ny = pos[1] + dy[j] * s;
                        if (nx < 0 || nx >= H || ny < 0 || ny >= W || copyMap[nx][ny] == 0)
                            continue;
                        if (copyMap[nx][ny] > 1) {
                            q.add(new int[] {nx, ny, copyMap[nx][ny]});
                        }
                        copyMap[nx][ny] = 0;
                    }
                }
            }
            down();
        }
        int remain = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (copyMap[i][j] != 0) {
                    remain++;
                }
            }
        }
        return remain;
    }

    public static void down() {
        for (int i = 0; i < W; i++) {
            ArrayList<Integer> l = new ArrayList<>();
            for (int j = H-1; j >= 0; j--) {
                if (copyMap[j][i] > 0) {
                    l.add(copyMap[j][i]);
                    copyMap[j][i] = 0;
                }
            }
            int h = H;
            for (int v : l) {
                copyMap[--h][i] = v;
            }
        }
    }

    public static int[] getTop(int col) {
        for (int i = 0; i < H; i++) {
            if (copyMap[i][col] != 0) {
                return new int[] {i, col, copyMap[i][col]};
            }
        }
        return new int[] {-1};
    }

    public static int[][] copy(int[][] arr) {
        int[][] copyMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copyMap[i][j] = arr[i][j];
            }
        }
        return copyMap;
    }

    public static class Solution_1238_D4_Contact {
        static int N, start;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();
            for (int tc = 1; tc <= 10; tc++) {
                sb.append("#").append(tc).append(" ");
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                start = Integer.parseInt(st.nextToken());

                List<Integer>[] list = new ArrayList[101];
                for (int i = 1; i < 101; i++) {
                    list[i] = new ArrayList<>(101 >> 1);
                }

                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N/2; i++) {
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());
                    list[from].add(to);
                }

                Queue<Integer> queue = new ArrayDeque<>();
                boolean[] visited = new boolean[101];

                queue.add(start);
                int max = start;
                visited[start] = true;
                while (!queue.isEmpty()) {
                    max = 0;
                    for (int i = 0, size = queue.size(); i < size; i++) {
                        int cur = queue.poll();
                        max = Math.max(max, cur);
                        for (int j = 0; j < list[cur].size(); j++) {
                            int adj = list[cur].get(j);
                            if (!visited[adj]) {
                                visited[adj] = true;
                                queue.add(adj);
                            }
                        }
                    }
                }
                sb.append(max).append("\n");
            }
            System.out.println(sb);
        }
    }
}
