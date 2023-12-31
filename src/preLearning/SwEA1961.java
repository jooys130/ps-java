package preLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SwEA1961 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            System.out.println("#" + tc);
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 시계 방향 90도 회전
            int[][] degree90 = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    degree90[j][N-1-i] = arr[i][j];
                }
            }

            // 180도 회전
            int[][] degree180 = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    degree180[N-1-i][N-1-j] = arr[i][j];
                }
            }

            // 270도 회전
            int[][] degree270 = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    degree270[N-1-j][i] = arr[i][j];
                }
            }

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    System.out.print(degree90[row][col]);
                }
                System.out.print(" ");
                for (int col = 0; col < N; col++) {
                    System.out.print(degree180[row][col]);
                }
                System.out.print(" ");
                for (int col = 0; col < N; col++) {
                    System.out.print(degree270[row][col]);
                }
                System.out.println();
            }
        }
    }

}
