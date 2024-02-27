package graph;


import java.io.*;
import java.util.*;

public class Main_1647_G4_도시분할계획 {
    static int N, M;
    static List<Node>[] list;
    static class Node implements Comparable<Node> {
        int to;
        int w;
        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        /*
            두 개로 나누는 조합을 구할 게 아니라
            가장 높은 가중치를 나누는 간선 하나를 삭제해주면 된다!
         */

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        System.out.println(prim(1)); // 1번 노드부터 탐색
    }

    public static int prim(int node) {
        Queue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        pq.add(new Node(node, 0));

        int result = 0;
        int connect = 0;
        int maxW = 0; // 가장 가중치가 큰 값 저장하기 위함
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int to = cur.to;
            int w = cur.w;
            if (visited[to]) continue;

            visited[to] = true;
            result += w;
            // 1이 최대일수도 있으니 미리 기록
            maxW = Math.max(maxW, w);
            // N개 모두 탐색
            if (++connect == N) break;

            for (Node n : list[to]) {
                if (!visited[n.to]) pq.add(n);
            }
        }
        return result - maxW;
    }
}