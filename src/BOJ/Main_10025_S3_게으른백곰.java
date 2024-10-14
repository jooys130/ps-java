package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10025_S3_게으른백곰 {
    static int N, K;
    static int[] ices;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ices = new int[1_000_001];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int ice = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            ices[idx] = ice;
        }
        int sum = 0;
        int sdw = 2 * K + 1;
        for (int i = 0; i <= 1_000_000; i++) {
            if (i - sdw >= 0) sum -= ices[i - sdw];
            sum += ices[i];
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }
}
