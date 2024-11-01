package BOJ;

import java.io.*;
import java.util.*;

public class Main_1018_S4_체스판다시칠하기 {
    static int N, M;
    static char[][] board;
    static char[][] case1;
    static  char[][] case2;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        case1 = new char[N][M];
        case2 = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        init();
        for (int i = 0; i < N-7; i++) {
            for (int j = 0; j < M-7; j++) {
                ans = Math.min(ans, getCount(i, j));
            }
        }
        System.out.println(ans);
    }
    private static int getCount(int startX, int startY) {
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = startX; i < startX + 8; i++) {
            for (int j = startY; j < startY + 8; j++) {
                if (board[i][j] != case1[i][j]) cnt1++;
                if (board[i][j] != case2[i][j]) cnt2++;
            }
        }
        return Math.min(cnt1, cnt2);
    }
    public static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        case1[i][j] = 'W';
                        case2[i][j] = 'B';
                    } else if (j % 2 == 1) {
                        case1[i][j] = 'B';
                        case2[i][j] = 'W';
                    }
                } else {
                    if (j % 2 == 0) {
                        case1[i][j] = 'B';
                        case2[i][j] = 'W';
                    } else if (j % 2 == 1) {
                        case1[i][j] = 'W';
                        case2[i][j] = 'B';
                    }
                }
            }
        }
    }
}
