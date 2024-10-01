package PRO;

import java.util.*;
class Solution_Lv3_가장먼노드 {
    static List<Integer>[] arr;
    static int[] dist;
    static boolean[] visited;
    static Queue<int[]> q;
    static int maxDepth;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        arr = new ArrayList[n+1];
        dist = new int[n+1];
        visited = new boolean[n+1];
        q = new ArrayDeque<>();
        for (int i = 1; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge.length; i++) {
            arr[edge[i][0]].add(edge[i][1]);
            arr[edge[i][1]].add(edge[i][0]);
        }
        q.add(new int[] {1, 0});
        visited[1] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int next : arr[cur[0]]) {
                if (!visited[next]) {
                    q.add(new int[] {next, cur[1] + 1});
                    visited[next] = true;
                    dist[next] = cur[1] + 1;
                    maxDepth = Math.max(dist[next], maxDepth);
                }
            }
        }
        int cnt = 0;
        for (int i = 1; i < n+1; i++) {
            if (dist[i] == maxDepth) {
                cnt++;
            }
        }
        return cnt;
    }
}