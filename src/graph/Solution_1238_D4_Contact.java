package graph;


import java.io.*;
import java.util.*;

public class Solution_1238_D4_Contact {
    static int N, start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            List<Integer>[] list = new ArrayList[101];
            for (int i = 1; i < 101; i++) {
                list[i] = new ArrayList<>(101 >> 1);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N/2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
            }

            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[101];

            queue.add(start);
            int max = start;
            visited[start] = true;
            while (!queue.isEmpty()) {
                max = 0;
                for (int i = 0, size = queue.size(); i < size; i++) {
                    int cur = queue.poll();
                    max = Math.max(max, cur);
                    for (int j = 0; j < list[cur].size(); j++) {
                        int adj = list[cur].get(j);
                        if (!visited[adj]) {
                            visited[adj] = true;
                            queue.add(adj);
                        }
                    }
                }
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}
