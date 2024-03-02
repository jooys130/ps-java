package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2098_G1_외판원순회 {
    /*
        N이 "16 이하"일 때 "DP + 비트마스크"
        1. 비트마스크로 도시 방문 상태 저장
        2. DP로 특정 도시들을 방문한 상태일 때 최소 비용 저장하여 재사용
    */

    static int N;
    static int[][] W;
    static int[][] dp;
    // MAX_VALUE로 하면 재귀 호출로 인한 연산시, 오버플로우 발생 가능
    static final int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][(1<<N)-1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        /*
            시작 노드를 0으로 가정
            어차피 모든 노드를 방문해야하므로(사이클이므로) 상관 없다
         */
        System.out.println(TSP(0, 1));
    }

    public static int TSP(int cur, int visited) {

        // 모든 지점을 방문한 경우
        if (visited == (1 << N) - 1) {
            // 순회할 수 없는 경우
            if (W[cur][0] == 0) {
                return INF;
            }
            return W[cur][0];
        }

        // 중복 방문한 경우
        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }
        // 경로가 없는 경우
        dp[cur][visited] = INF;

        for (int i = 0; i < N; i++) {
            // 방문했다면
            if((visited & (1 << i)) != 0) continue;
            // 경로가 없다면
            if(W[cur][i] == 0) continue;
            // 최소 비용 갱신
            dp[cur][visited] = Math.min(W[cur][i] + TSP(i, visited | (1 << i)), dp[cur][visited]);
        }

        return dp[cur][visited];
    }

}