package BOJ;

import java.io.*;
import java.util.*;

public class Main_3055_G4_탈출 {
    /*
        고슴도치와 물은 인접한 네칸으로 이동한다
        고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다 => 물 먼저 퍼트리기
        고슴도치는 물과 같은 레벨을 보장하여 퍼트려야 한다
     */
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {-1, 0, 0, 1};
    static Queue<int[]> q;
    static List<int[]> water;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();
        map = new char[R][C];
        visited = new boolean[R][C];
        water = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {
                    q.add(new int[] {i, j, 0});
                    visited[i][j] = true;
                }
                else if (map[i][j] == '*') water.add(new int[] {i, j});
            }
        }
        while(!q.isEmpty()) {
//            for (int i = 0; i < R; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println();
            // 물 퍼트리기
            Queue<int[]> nw = new ArrayDeque<>();
            for (int i = 0; i < water.size(); i++) {
                int[] l = water.get(i);
                for (int j = 0; j < 4; j++) {
                    int nx = l[0] + dx[j];
                    int ny = l[1] + dy[j];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        nw.add(new int[] {nx, ny});
                    }
                }
            }
            water = new ArrayList<>();
            while(!nw.isEmpty()) {
                water.add(nw.poll());
            }
            // 고슴도치 위치 이동
            int qSize = q.size();
            for (int o = 0; o < qSize; o++) {
                int[] pos = q.peek();
                for (int i = 0; i < 4; i++) {
                    int nx = pos[0] + dx[i];
                    int ny = pos[1] + dy[i];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
                    if (map[nx][ny] == 'D') {
                        System.out.println(pos[2] + 1);
                        return;
                    } else if (map[nx][ny] == '.'){
                        map[nx][ny] = 'S';
                        visited[nx][ny] = true;
                        q.add(new int[] {nx, ny, pos[2]+1});
                    }
                }
                q.poll();
            }
        }
        System.out.println("KAKTUS");
    }
}
