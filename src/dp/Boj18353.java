package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj18353 {
    static int n;
    static int arr[];
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) { // next
            for (int j = 0; j < i; j++) { // prev
                if (arr[i] < arr[j]) { // 증가한다면
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxValue = 0;
        for (int i = 0; i < n; i++){
            maxValue = Math.max(maxValue, dp[i]);
        }

        System.out.println(n - maxValue);

    }
}

