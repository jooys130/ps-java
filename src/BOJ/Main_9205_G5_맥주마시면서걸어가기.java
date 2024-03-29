package BOJ;

import java.io.*;
import java.util.*;

public class Main_9205_G5_맥주마시면서걸어가기 {
    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
    static Pos home;
    static Pos[] convi;
    static Pos festi;
    static int N;
    static boolean[] visited;
    static Queue<Pos> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            // input
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            home = new Pos(x, y);
            convi = new Pos[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                convi[i] = new Pos(x, y);
            }
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            festi = new Pos(x, y);
            // bfs
            visited = new boolean[N];
            q = new ArrayDeque<>();
            q.add(home);
            boolean flag = false;
            while(!q.isEmpty()) {
                Pos pos = q.poll();
                if (dis(pos, festi) <= 1000) {
                    flag = true;
                    break;
                }
                for (int i = 0; i < N; i++) {
                    if (visited[i] || dis(convi[i], pos) > 1000) continue;
                    q.add(convi[i]);
                    visited[i] = true;
                }
            }
            System.out.println(flag ? "happy" : "sad");
        }
    }
    public static int dis(Pos p1, Pos p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}