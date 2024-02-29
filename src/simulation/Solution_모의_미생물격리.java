package simulation;

import java.io.*;
import java.util.*;

public class Solution_모의_미생물격리 {

    static class Group implements Comparable<Group> {
        int x, y, cnt, d;
        Group(int x, int y, int cnt, int d) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.d = d;
        }
        @Override
        public int compareTo(Group o) {
            return o.cnt - this.cnt;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + d + " " + cnt;
        }
    }
    static List<Group> list;
    static int[][] cnt;
    static int N, M, K;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                list.add(new Group(x, y, cnt, d));
            }

            for (int i = 0; i < M; i++) {
                cnt = new int[N][N];
                Collections.sort(list);
                move();
            }
            System.out.println("#" + tc + " " + sum());
        }
    }
    public static void move() {
        for (int i = 0; i < list.size(); i++) {
            Group a = list.get(i);
            if (a.cnt == 0) continue;
            // 이동할 곳
            a.x += dx[a.d];
            a.y += dy[a.d];
            if (a.x == 0 || a.x == N-1 || a.y == 0 || a.y == N-1) {
                if (a.cnt == 1) a.cnt = 0;
                else {
                    a.cnt /= 2;
                    if (a.d % 2 == 0) a.d += 1;
                    else a.d -= 1;
                }
            }
            if (cnt[a.x][a.y] != 0) {
                cnt[a.x][a.y] += a.cnt;
                a.cnt = 0;
            } else {
                cnt[a.x][a.y] = a.cnt;
            }
        }
        for(Group g : list) {
            if (g.cnt > 0) {
                g.cnt = cnt[g.x][g.y];
            }
        }
    }

    public static int sum() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cnt[i][j] > 0) ans += cnt[i][j];
            }
        }
        return ans;
    }
}
