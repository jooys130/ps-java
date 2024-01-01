package preLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SwEA7465 {

    static int N, E;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }
            visited = new boolean[N];
            count = 0;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    dfs(i);
                    count++;
                }
            }

            System.out.println("#" + tc + " " + count);
        }
    }

    private static void dfs(int node) {
        visited[node] = true;
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

}
