package BOJ;

import java.io.*;
import java.util.*;

public class Main_1260_S2_DFS와BFS {
    static int N, M, startNode;
    static List<Integer>[] edges;
    static StringBuilder sb;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        input();
        sb = new StringBuilder();
        visited = new boolean[N + 1];
        dfs(startNode);
        sb.append("\n");
        bfs();
        System.out.println(sb);
    }
    private static void dfs(int cur) {
        sb.append(cur).append(" ");
        visited[cur] = true;
        for (int next: edges[cur]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
    private static void bfs() {
        visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(startNode);
        visited[startNode] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");
            visited[cur] = true;
            for (int next : edges[cur]) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
    }
    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());
        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        // 작은 것부터 방문
        for (int i = 1; i <= N; i++) {
            Collections.sort(edges[i]);
        }
    }
}
