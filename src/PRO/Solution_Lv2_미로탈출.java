package PRO;

import java.util.*;
class Solution {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] start, end;
    static int[] l; // 무조건 1개
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public int solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        map = new char[R][C];
        start = new int[2];
        end = new int[2];
        l = new int[2];
        for(int i = 0; i < R; i++) {
            String s = maps[i];
            for(int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    start[0] = i; start[1] = j;
                } else if (map[i][j] == 'L') {
                    l[0] = i; l[1] = j;
                } else if (map[i][j] == 'E') {
                    end[0] = i; end[1] = j;
                }
            }
        }
        int first = find(start, l);
        if (first == -1) return -1;
        int second = find(l, end);
        return second == -1 ? -1 : first + second;
    }
    public static int find(int[] s, int[] e) {
        visited = new boolean[R][C];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {s[0], s[1], 0});
        visited[s[0]][s[1]] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || map[nx][ny] == 'X') continue;
                if (nx == e[0] && ny == e[1]) {
                    return cur[2] + 1;
                }
                q.add(new int[] {nx, ny, cur[2] + 1});
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}