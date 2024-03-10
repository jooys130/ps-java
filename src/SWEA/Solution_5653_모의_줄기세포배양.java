package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5653_모의_줄기세포배양 {

    static int N, M, K;

    static class Node implements Comparable<Node> {

        int x;
        int y;
        int l;
        int time;

        Node(int x, int y, int l, int time) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.time = time;
        }

        @Override
        public int compareTo(Node o1) {
            return o1.l - this.l;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + l;
        }
    }

    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};
    static Queue<Node> pq;
    static Queue<Node> q;
    static boolean[][] visited;

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
            K = Integer.parseInt(st.nextToken());

            visited = new boolean[N+K+1][M+K+1];
            pq = new PriorityQueue<>();
            q = new ArrayDeque<>();

            int start = K/2 + 1;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x != 0) {
                        visited[start + i][start+j] = true;
                        pq.add(new Node(start + i, start + j, x, x));
                    }
                }
            }

            while(K-->0) {
                while(!pq.isEmpty()) {
                    Node cur = pq.poll();
                    cur.time--;

                    // 활성화 상태
                    if (cur.time < 0) {
                        for (int i = 0; i < 4; i++) {
                            int nx = cur.x + dx[i];
                            int ny = cur.y + dy[i];
                            if(visited[nx][ny]) continue;
                            visited[nx][ny] = true;
                            // 비활성 상태
                            q.add(new Node(nx, ny, cur.l, cur.l));
                        }
                    }
                    // 죽은 상태
                    if (cur.time + cur.l == 0) {
                        continue;
                    }

                    q.add(cur);
                }
                while(!q.isEmpty()) {
                    pq.add(q.poll());
                }
            }
            sb.append(pq.size()).append("\n");
        }
        System.out.println(sb);
    }
}
