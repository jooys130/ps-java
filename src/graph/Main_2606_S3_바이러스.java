package graph;

import java.util.*;
import java.io.*;

public class Main_2606_S3_바이러스 {
    static int N, E;
    // 인접 행렬 대신 인접 리스트를 사용하여 최적화 시도
    static List<List<Integer>> board;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        board = new ArrayList<List<Integer>>();
        for (int i = 0; i < N; i++) {
            board.add(new ArrayList<Integer>());
        }

        visited = new boolean[N];
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            board.get(from).add(to);
            board.get(to).add(from);
        }

        bfs(0);
        System.out.println(ans);
    }

    public static void bfs(int node) {
        Deque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < board.get(node).size(); i++) {
            q.add(board.get(node).get(i));
        }
        visited[node] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            if (!visited[cur]) {
                for (int i = 0; i < board.get(cur).size(); i++) {
                    q.add(board.get(cur).get(i));
                }
                visited[cur] = true;
                ans++;
            }
        }
    }
}

