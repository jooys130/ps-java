package PRO;

import java.util.*;
class Solution_Lv2_리코쳇로봇 {
    static int N, M;
    static char[][] arr;
    static boolean[][] visited;
    static class Node {
        int x, y, cnt;
        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static Node end;
    static Queue<Node> q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        arr = new char[N][M];
        visited = new boolean[N][M];
        q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = board[i].charAt(j);
                if (arr[i][j] == 'R') {
                    q.add(new Node(i, j, 0));
                    visited[i][j] = true;
                }
                else if (arr[i][j] == 'G') end = new Node(i, j, 0);
            }
        }
        while(!q.isEmpty()) {
            Node pos = q.poll();
            // System.out.println(pos.x + " " + pos.y + " " + pos.cnt);
            if (pos.x == end.x && pos.y == end.y) return pos.cnt;
            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                while (true) {
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] == 'D') {
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }
                if (!visited[nx][ny]) {
                    q.add(new Node(nx, ny, pos.cnt+1));
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }
}
