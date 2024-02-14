package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_S3_설탕배달_주연수 {

    /*
     * 수행 시간:	124 ms
     * 메모리:	14,204 KB
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        while (N >= 0) {
            if (N % 5 == 0) {
                ans += (N / 5);
                System.out.println(ans);
                return;
            }
            N -= 3;
            ans++;
        }
        System.out.println(-1);
    }
}
