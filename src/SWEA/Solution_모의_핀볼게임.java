package SWEA;

import java.io.*;
import java.util.*;

public class Solution_모의_핀볼게임 {
    static int N;
    static int[][] map;
    static Pos[][] holls; // 웜홀 어떤 자료구조?
    static class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return x + " " + y;
        }
    }
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int maxScore;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc < T+1; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            holls = new Pos[5][2];
            for (int i = 0; i < N; i++) {
                StringTokenizer st= new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        if (holls[map[i][j]-6][0] == null) {
                            holls[map[i][j]-6][0] = new Pos(i, j);
                        } else if (holls[map[i][j]-6][1] == null) {
                            holls[map[i][j]-6][1] = new Pos(i, j);
                        }
                    }
                }
            }
            maxScore = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            int score = go(i, j, k);
                            if (maxScore < score) maxScore = score;
                        }
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }
        System.out.println(sb);
    }
    public static int go(int x, int y, int d) {
        int score = 0;
        int nx = x;
        int ny = y;
        while(true) {
            // 계속 갱신할 수 있도록
            nx += dx[d];
            ny += dy[d];
            // System.out.println(nx + " " + ny);

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                // 벽 -> 어차피 반대 방향으로 가면 왔던 곳으로 돌아감
                return 2 * score + 1;
            } else if ((nx == x && ny == y) || map[nx][ny] == -1) {
                // 처음 위치로 돌아오거나 블랙홀에 가는 경우
                break;
            } else if (map[nx][ny] >= 1 && map[nx][ny] <= 5) {
                // 블록 => 벽과 마찬가지
                d = change(d, map[nx][ny]);
                score++;
                if (d == -1) {
                    return 2 * score - 1;
                }
            } else if (map[nx][ny] >= 6) {
                // 맨홀 위치 옮기기
                Pos[] holl = holls[map[nx][ny]-6];
                if (holl[0].x == nx && holl[0].y == ny) {
                    nx = holl[1].x;
                    ny = holl[1].y;
                } else {
                    nx = holl[0].x;
                    ny = holl[0].y;
                }
            }
        }
        return score;
    }
    public static int change(int dir, int type) {
        // 좌 우 상 하
        if (type == 1) {
            // 하 -> 우 / 좌 -> 상
            if (dir == 3) return 1;
            else if (dir == 0) return 2;
        } else if (type == 2) {
            // 좌 -> 하 / 상 -> 우
            if (dir == 0) return 3;
            else if (dir == 2) return 1;
        } else if (type == 3) {
            // 우 -> 하 / 상 -> 좌
            if (dir == 1) return 3;
            else if (dir == 2) return 0;
        } else if (type == 4) {
            // 하 -> 좌 / 우 -> 상
            if (dir == 3) return 0;
            else if (dir == 1) return 2;
        }
        return -1;
        // return dir % 2 == 0 ? dir+1 : dir-1;
    }
}
