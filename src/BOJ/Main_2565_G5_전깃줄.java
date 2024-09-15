package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2565_G5_전깃줄 {

    /*
        역으로 접근
        최대 설치 가능 개수 => N - (겹치지 않게 설치할 수 있는 개수)
        겹치지 않게 설치할 수 있는 개수를 구할 때 LIS 사용

        (예시)
        start = [1, 2, 3, 4, 5, 6]
        end = [2, 3, 4, 5, 6, 1]
        start 기준 1, 2, 3, 4, 5는 겹치지 않게 설치할 수 있다
        즉 (1, 6)인 와이어만 없애면 된다

    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] wire = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            wire[i] = new int[] {start, end};
        }

        Arrays.sort(wire, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (wire[i][1] > wire[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] == 0) {
                dp[i] = 1;
            }
        }

        Arrays.sort(dp);
        System.out.println(N - dp[N-1]);
    }

}
