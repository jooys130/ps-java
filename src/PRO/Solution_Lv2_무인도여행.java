package PRO;

import java.util.*;
class Solution_Lv2_무인도여행 {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<int[]> q;
    static List<Integer> arr;
    static int sum;
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                String tmp = maps[i].substring(j, j+1);
                if (tmp.equals("X")) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = Integer.parseInt(tmp);
                }
            }
        }
        q = new ArrayDeque<>();
        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    q.add(new int[] {i, j});
                    visited[i][j] = true;
                    sum = board[i][j];
                    arr.add(bfs());
                }
            }
        }
        Collections.sort(arr);
        int S = arr.size();
        if (S == 0) return new int[] {-1};
        int[] answer = new int[S];
        for (int i = 0; i < S; i++) {
            answer[i] = arr.get(i);
        }
        return answer;
    }
    public int bfs() {
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || board[nx][ny] == 0) {
                    continue;
                }
                sum += board[nx][ny];
                q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
        return sum;
    }
}