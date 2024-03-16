package BOJ;

import java.io.*;
import java.util.*;

public class Main_15591_G5_MooTube {
    static int N, Q;
    static List<int[]>[] USADO;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        USADO = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            USADO[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            USADO[to].add(new int[] {from, cost});
            USADO[from].add(new int[] {to, cost});
        }

        for (int i = 0; i < Q; i++) {
            int ans = 0;
            boolean[] visited = new boolean[N+1];
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Queue<Integer> q = new ArrayDeque<>();
            q.add(v);
            visited[v] = true;
            while(!q.isEmpty()) {
                // System.out.println(q);
                int cur = q.poll();
                for (int[] next : USADO[cur]) {
                    if (!visited[next[0]] && next[1] >= k) {
                        visited[next[0]] = true;
                        q.add(next[0]);
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }

    }
}
