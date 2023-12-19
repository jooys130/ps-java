package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class BoJ16235 {

    static int N, M, K;
    static int[][] A;
    static Queue<Integer> queue;
    static int[][] food;
    static int[][] deadTreeCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 기본 양분의 양
        food = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                food[i][j] = 5;
            }
        }

        // 추가될 양분의 양
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        // 구입한 M개의 나무를 땅에 심음: 나이 기반
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int age = Integer.parseInt(stringTokenizer.nextToken());

        }

        for (int year = 0; year < K; year++) {
            deadTreeCount = new int[N][N];
            ss();
            fw();
        }
    }
}
