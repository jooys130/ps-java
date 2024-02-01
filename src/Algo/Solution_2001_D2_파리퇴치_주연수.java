package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2001_D2_파리퇴치_주연수 {

    /*
     * 수행시간 :   111 ms
     * 메모리  : 18,224 kb
     * 푼 방법 : (0, 0) ~ (N-M, N-M) 좌표에 대해 M * M 만큼 합의 최댓값
     */

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = Integer.MIN_VALUE;
            for (int i = 0; i <= N - M; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int sum = killFlies(i, j);
                    answer = Math.max(answer, sum);
                }
            }

            System.out.printf("#%d %d%n", tc, answer);
        }
    }

    private static int killFlies(int i, int j) {
        int flies = 0;
        for (int k = 0; k < M; k++) {
            for (int l = 0; l < M; l++) {
                flies += map[i + k][j + l];
            }
        }
        return flies;
    }

}
