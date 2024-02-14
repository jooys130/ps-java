package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1992_S1_쿼드트리_주연수 {

    /*
     * 시간:  128 ms
     * 메모리: 14148 KB
     */

    static int N;
    static int[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        compress(N, 0, 0);
        System.out.println(sb);
    }

    private static void compress(int size, int x, int y) {
        if (check(size, x, y)) {
            sb.append(map[x][y]);
        } else {
            sb.append('(');
            compress(size / 2, x, y);
            compress(size / 2, x, y + size / 2);
            compress(size / 2, x + size / 2, y);
            compress(size / 2, x + size / 2, y + size / 2);
            sb.append(')');
        }
    }

    private static boolean check(int size, int x, int y) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[x][y] != map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
