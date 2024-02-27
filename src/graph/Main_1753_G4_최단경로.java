package graph;

import java.io.*;
import java.util.*;

public class Main_1753_G4_최단경로 {

    static int V, E;
    static int INF = Integer.MAX_VALUE;
    static List<Node>[] list;
    static int[] minCost;
    static boolean[] visited;

    static class Node implements Comparable<Node> {

        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        list = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, cost)); // 방향 그래프
        }

        visited = new boolean[V + 1];
        minCost = new int[V + 1];
        Arrays.fill(minCost, INF);

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        minCost[start] = 0;
        visited[start] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (int i = 0; i < list[cur.to].size(); i++) {
                int next = list[cur.to].get(i).to;
                int nextCost = list[cur.to].get(i).cost;
                if (!visited[next] && cur.cost + nextCost < minCost[next]) {
                    minCost[next] = cur.cost + nextCost;
                    pq.add(new Node(next, minCost[next]));
                }
            }
            visited[cur.to] = true;
        }

        for (int i = 1; i <= V; i++) {
            System.out.println(minCost[i] == INF ? "INF" : minCost[i]);
        }
    }
}
