package BOJ;

import java.io.*;

public class Main_9095_S3_123더하기 {
    static int N;
    static int[] nums = {1, 2, 3};
    static int[] arr;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            ans = 0;
            // dfs(0);
            // System.out.println(ans);
            dp();
        }
    }
    public static void dfs(int sum) {
        if(sum > N) return;
        if (sum == N) {
            ans++;
            return;
        }
        for (int i = 0; i < 3; i++) {
            dfs(sum+nums[i]);
        }
    }
    public static void dp() {
        arr = new int[Math.max(N+1, 4)];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;
        for (int i = 4; i < Math.max(N+1, 4); i++) {
            arr[i] = arr[i-3] + arr[i-2] + arr[i-1];
        }
        System.out.println(arr[N]);
    }
}
