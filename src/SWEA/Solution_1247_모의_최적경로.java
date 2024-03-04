package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_모의_최적경로 {

    static class Pos {

        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static Pos home, company;
    static Pos[] clients;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            clients = new Pos[N];
            for (int i = 0; i < N; i++) {
                clients[i] = new Pos(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            }

            ans = Integer.MAX_VALUE;
            visited = new boolean[N];
            dfs(0, 0, home.x, home.y);

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(ans);
            System.out.println(sb);
        }
    }

    public static void dfs(int cnt, int dis, int x, int y) {
        if (cnt == N) {
            ans = Math.min(ans, dis + calc(x, y, company.x, company.y));
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nx = clients[i].x;
                int ny = clients[i].y;
                dfs(cnt + 1, dis + calc(x, y, nx, ny), nx, ny);
                visited[i] = false;
            }
        }
    }

    public static int calc(int x, int y, int nx, int ny) {
        return Math.abs(x - nx) + Math.abs(y - ny);
    }

}
