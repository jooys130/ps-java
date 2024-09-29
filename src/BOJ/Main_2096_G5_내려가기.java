package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2096_G5_내려가기 {
    static int N;
    static int[][] map;
    static int[][] minDp;
    static int[][] maxDp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        minDp = new int[N]:q!:q!
    [3];
        maxDp = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        minDp[0] = Arrays.copyOf(map[0], 3);
        maxDp[0] = Arrays.copyOf(map[0], 3);
        for (int i = 1; i < N; i++) {
            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + map[i][0];
            minDp[i][1] = Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]) + map[i][1];
            minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + map[i][2];

            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + map[i][0];
            maxDp[i][1] = Math.max(Math.max(maxDp[i-1][0], maxDp[i-1][1]), maxDp[i-1][2]) + map[i][1];
            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + map[i][2];
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minDp[N-1][i]);
            max = Math.max(max, maxDp[N-1][i]);
        }
        System.out.println(max + " " + min);
    }
}
