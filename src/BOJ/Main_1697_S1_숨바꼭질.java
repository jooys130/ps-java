package BOJ;

import java.util.*;
import java.io.*;

public class Main_1697_S1_숨바꼭질 {
    static int x, target;
    static class Node implements Comparable<Node> {
        int v;
        int depth;
        Node(int v, int depth) {
            this.v = v;
            this.depth = depth;
        }
        @Override
        public int compareTo(Node o) {
            return this.depth - o.depth;
        }
    }
    static Deque<Node> pq = new ArrayDeque<>();
    static boolean[] visited = new boolean[100_001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        pq.offer(new Node(x, 0));
        visited[x] = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.v == target) {
                System.out.println(cur.depth);
                break;
            }
            if (cur.v + 1 <= 100_000 && !visited[cur.v + 1]) {
                pq.offer(new Node(cur.v + 1, cur.depth + 1));
                visited[cur.v + 1] = true;
            }
            if (cur.v - 1 >= 0 && !visited[cur.v - 1]) {
                pq.offer(new Node(cur.v - 1, cur.depth + 1));
                visited[cur.v - 1] = true;
            }
            if (cur.v * 2 <= 100_000 && !visited[cur.v * 2]) {
                pq.offer(new Node(cur.v * 2, cur.depth + 1));
                visited[cur.v * 2] = true;
            }
        }
    }
}
