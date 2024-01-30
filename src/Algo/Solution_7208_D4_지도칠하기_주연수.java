package Algo;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_7208_D4_지도칠하기_주연수 {

    static int N;           // 나라 개수
    static int[] colors;    // 나라 별 색상
    static int[] numbers;   // 순열
    static int[][] adjArr;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            N = Integer.parseInt(br.readLine());
            colors = new int[N];
            numbers = new int[N];
            adjArr = new int[N][N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                colors[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    adjArr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            permutation(0);
            
            System.out.printf("#%d %d%n", tc, min);
        }
    }

    private static void permutation(int depth) {
        if (depth == N) {
            // 인접 노드에 대해 중복이 있는가?
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (adjArr[i][j] == 1 && numbers[i] == numbers[j]) {
                        return;
                    }
                }
            }

            // 중복이 없다면
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (numbers[i] != colors[i]) {
                    count++;
                }
            }
            min = Math.min(min, count);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            numbers[depth] = i;
            permutation(depth + 1);
        }
    }

}