package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int from, to, len;
    public Node(int from, int to, int len) {
        this.from = from;
        this.to = to;
        this.len = len;
    }
    @Override
    public int compareTo(Node o) {
        if (this.len == o.len) {
            if (this.from == o.from) return this.to - o.to;
            return this.from - o.from;
        }
        return this.len - o.len;
    }

    @Override
    public String toString() {
        return from + " " + to + " " + len;
    }
}

public class Main_17472_다리만들기2_주연수 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};
    static int islandCnt;
    static PriorityQueue<Node> bridge;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islandCnt = numbering();

        bridge = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 2; k++) {
                        makeCandidates(i, j, k, -1, map[i][j]);
                    }
                }
            }
        }

        System.out.println(makeBridge());
    }

    public static int makeBridge() {
        makeSet();

        int ans = 0;
        int bridgeNum = 0;
        while(!bridge.isEmpty()) {
            Node cur = bridge.poll();
            int from = cur.from; int to = cur.to; int len = cur.len;
            if (find(from) != find(to)) {
                union(from, to);
                bridgeNum++;
                ans += len;
            }
        }
        if (bridgeNum != islandCnt - 2) return -1;
        return ans;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) parents[a] = b;
        else parents[b] = a;
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    private static void makeSet() {
        parents = new int[islandCnt];
        for (int i = 1; i < islandCnt; i++) {
            parents[i] = i;
        }
    }

    public static void makeCandidates(int x, int y, int dir, int cnt, int num) {
        if (map[x][y] != 0 && map[x][y] != num) {
            // 다른 섬 만남
            if (cnt >= 2) {
                boolean duplicated = false;
                for (Node n : bridge) {
                    if (n.from == num && n.to == map[x][y] && n.len == cnt) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    bridge.offer(new Node(num, map[x][y], cnt));
                }
            }
            return;
        }
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == num) return;
        makeCandidates(nx, ny, dir, cnt+1, num);
    }
    public static int numbering() {
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, cnt++);
                }
            }
        }
        return cnt;
    }
    public static void dfs(int i, int j, int cnt) {
        visited[i][j] = true;
        map[i][j] = cnt;
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (!visited[nx][ny] && map[nx][ny] == 1) {
                visited[nx][ny] = true;
                dfs(nx, ny, cnt);
            }
        }
    }
}

