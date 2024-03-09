package SWEA;

import java.io.*;
import java.util.*;


public class Solution_1803_D4_ShortestPathFaster {
    static int N, M;
    static class Node implements Comparable<Node>{
        int vertex;
        long cost;
        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return (int) (this.cost - o.cost);
        }
        @Override
        public String toString() {
            return vertex + " " + cost;
        }
    }
    static List<Node>[] adjList;
    static int start, end;
    static boolean[] visited;
    static long[] minEdge;
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            visited = new boolean[N+1];
            minEdge = new long[N+1];
            adjList = new ArrayList[N+1];
            for (int i = 0; i < N+1; i++) {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                adjList[from].add(new Node(to, cost));
                adjList[to].add(new Node(from, cost));
                // 무방향
            }

            Arrays.fill(minEdge, INF);
            minEdge[start] = 0;
            go(start);
            sb.append(minEdge[end]).append('\n');
        }
        System.out.println(sb);
    }
    public static void go(int node) {
        Queue<Integer> q = new ArrayDeque<>();
        visited[node] = true;
        q.add(node);
        while(!q.isEmpty()) {
            int cur = q.poll();
//            System.out.println("queue" + cur);
            visited[cur] = true;
            for (Node next : adjList[cur]) {
//                System.out.println(next);
                if (!visited[next.vertex] && next.cost + minEdge[cur] < minEdge[next.vertex]) {
                    minEdge[next.vertex] = next.cost + minEdge[cur];
                    q.add(next.vertex);
                }
            }

        }
    }
}
