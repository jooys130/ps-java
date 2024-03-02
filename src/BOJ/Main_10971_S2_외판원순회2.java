package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10971_S2_외판원순회2 {
    /*
        N이 "10 이하"일 때 "완전 탐색" => 10!
    */
    static int N;
    static int[][] W;
    static int minCost;
    static int[] numbers;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        numbers = new int[N];
        isSelected = new boolean[N];

        minCost = Integer.MAX_VALUE;
        permutation(0);
        System.out.println(minCost);
    }
    public static void permutation(int idx) {
        if(idx == N) {
            int cost = 0;
            for (int i = 0; i < N; i++) {
                int start = numbers[i];
                int end = numbers[(i+1) % N];
                if (W[start][end] == 0) return;
                if (cost > minCost) return;
                cost += W[start][end];
            }
            minCost = Math.min(cost, minCost);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            numbers[idx] = i;
            isSelected[i] = true;
            permutation(idx+1);
            isSelected[i] = false;
        }
    }
}
