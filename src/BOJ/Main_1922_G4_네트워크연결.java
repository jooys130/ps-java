package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1922_G4_네트워크연결 {
    static int V, E;
    static boolean[] visited;
    static ArrayList<Node>[] edges; // 간선 기반으로

    static class Node implements Comparable<Node> {
        int to;
        int w;

        Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static PriorityQueue<Node> pq;
    static int minCost; // MST 최소 비용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        visited = new boolean[V + 1];
        edges = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[from].add(new Node(to, w));
            edges[to].add(new Node(from, w));
        }
        pq = new PriorityQueue<>();
        // 임의의 노드 추가
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) continue;

            visited[cur.to] = true;
            minCost += cur.w;

            for (Node n : edges[cur.to]) {
                if (visited[n.to]) continue;
                pq.add(new Node(n.to, n.w));
            }
        }
        System.out.println(minCost);
    }
}
