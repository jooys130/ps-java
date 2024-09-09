package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1654_S2_랜선자르기 {
    static int K; // 이미 기지고 있는 랜선의 수 K
    static int N; // 필요한 랜선의 수
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];
        int max = 0;
        for(int i=0; i<K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        // N개를 만들 수 있는 랜선의 최대 길이
        // 인덱스가 아닌 거리를 이분탐색 => min과 max
        long low = 1;
        long high = max;

        /*
            upper bound - 1
            200cm로 11개
            198cm로 11개
         */
        while (low <= high) {
            long mid = (low + high) / 2;
            // 랜선 개수 구하기
            int cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += arr[i] / mid;
            }

            if (cnt >= N) {
                // 길이를 늘려도 된다
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(high);
    }
}
