package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11404_G4_플로이드 {
    static int n, m;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        final int MAX = 100_001 * n;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 오버플로우 방지 - 간선의 최소 비용 * (전체 노드 개수)
                if (i != j) dp[i][j] = MAX;
            }
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp[x-1][y-1] = Math.min(dp[x-1][y-1], c);
        }

//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(dp[]));
//		}

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                // if (i == k) continue;
                for (int j = 0; j < n; j++) {
                    // if (j == k || j == i) continue;
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // (문제 조건) 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
                if (dp[i][j] == MAX) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}