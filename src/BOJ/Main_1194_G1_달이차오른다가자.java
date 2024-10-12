package BOJ;

import javax.swing.plaf.IconUIResource;
import java.security.Key;
import java.util.*;
import java.io.*;
public class Main_1194_G1_달이차오른다가자 {
    static final int NUM = 6;
    static int N, M;
    static char[][] map;
    static Queue<Node> q;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static class Node {
        int x;
        int y;
        int KEY;
        int dis;
        Node(int x, int y, int KEY, int dis) {
            this.x = x;
            this.y = y;
            this.KEY = KEY;
            this.dis = dis;
        }
        @Override
        public String toString() {
            return x + " " + y + " " + Integer.toBinaryString(KEY) + " => " + dis ;
        }
    }
    static boolean[][][] visited; // 왔던 길 되돌아감
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[(1 << (NUM+1)) - 1][N][M];
        q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '0') {
                    q.add(new Node(i, j, 0, 0));
                    visited[0][i][j] = true;
                }
            }
        }
        while(!q.isEmpty()) {
            Node cur = q.poll();
            // System.out.println(cur);
            if (map[cur.x][cur.y] == '1') {
                System.out.println(cur.dis);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                int nextKEY = cur.KEY;
                if (outOfRange(x, y) || map[x][y] == '#' || visited[nextKEY][x][y]) continue;
                if (map[x][y] - 'a' >= 0 && map[x][y] - 'a' <= 6) {
                    // 소문자일 때
                    nextKEY |= (1 << (map[x][y] - 'a'));
                } else if (map[x][y] - 'A' >= 0 && map[x][y] - 'A' <= 6) {
                    // 대문자인데 키가 없을 때
                    if ((nextKEY & (1 << (map[x][y] - 'A'))) != 0) {
                        q.add(new Node(x, y, nextKEY, cur.dis + 1));
                        visited[nextKEY][x][y] = true;
                    }
                    continue;
                }
                q.add(new Node(x, y, nextKEY, cur.dis + 1));
                visited[nextKEY][x][y] = true;
            }
        }
        System.out.println(-1);
    }
    static boolean outOfRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
