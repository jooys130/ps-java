package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1873_D3_상호의배틀필드_주연수 {

    /*
     * 실행시간: 116 ms
     * 메모리: 21,716 kb
     */

    static int H, W, N;
    static char[][] map;
    static char[] user;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int dir = -1;
    static int[] train;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String input;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc < T+1; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            train = new int[2];
            for (int i = 0; i < H; i++) {
                input = br.readLine();
                for (int j = 0; j < W; j++) {
                    char k = map[i][j] = input.charAt(j);
                    if (k == '^') { dir = 0; train[0] = i; train[1] = j; }
                    else if (k == 'v') { dir = 1; train[0] = i; train[1] = j; }
                    else if (k == '<') { dir = 2; train[0] = i; train[1] = j; }
                    else if (k == '>') { dir = 3; train[0] = i; train[1] = j; }
                }
            }
            N = Integer.parseInt(br.readLine());
            user = new char[N];
            input = br.readLine();
            for (int i = 0; i < N; i++) {
                execute(input.charAt(i));
            }
            sb.append('#').append(tc).append(" ");
            for (int j = 0; j < H; j++) {
                for (int k = 0; k < W; k++) {
                    sb.append(map[j][k]);
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }

    private static void execute(char c) {
        if (c == 'U') {
            dir = 0; map[train[0]][train[1]] = '^'; move(dir);
        } else if (c == 'D') {
            dir = 1; map[train[0]][train[1]] = 'v'; move(dir);
        } else if (c == 'L') {
            dir = 2; map[train[0]][train[1]] = '<'; move(dir);
        } else if (c == 'R') {
            dir = 3;
            map[train[0]][train[1]] = '>';
            move(dir);
        } else if (c == 'S') {
            int nx = train[0]; int ny = train[1];
            while (true) {
                nx += dx[dir];
                ny += dy[dir];
                if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
                    break;
                }
                if (map[nx][ny] == '*') {
                    map[nx][ny] = '.';
                    break;
                } else if (map[nx][ny] == '#') {
                    break;
                }
            }
        }
    }
    private static void move(int dir) {
        int nx = train[0] + dx[dir];
        int ny = train[1] + dy[dir];
        if (nx < 0 || nx >= H || ny < 0 || ny >= W) return;
        if (map[nx][ny] == '.') {
            map[nx][ny] = map[train[0]][train[1]];
            map[train[0]][train[1]] = '.';
            train[0] = nx; train[1] = ny;
        }
    }
}
