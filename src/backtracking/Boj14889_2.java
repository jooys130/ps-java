package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14889_2 {

    static int N;
    static int S[][];
    static int M;
    static boolean visited[];
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = N / 2;

        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N]; // 초기화 안 해도 됨

        dfs(0, 0);
        System.out.println(minValue);
    }

    private static void dfs(int n, int idx) {
        if (n == M) { // 절반만큼 선택했다면
            int aSum = 0;
            int bSum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i] && visited[j]) {
                        aSum += S[i][j];
                    } else if (!visited[i] && !visited[j]) {
                        bSum += S[i][j];
                    }
                }
            }
            minValue = Math.min(minValue, Math.abs(aSum - bSum));
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(n + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
