package Algo.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_S3_2407_조합 {

    /*
        nCr = n! / (n-r)! * r!
        W-C : 100C50 > 10 ^ 30
     */

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(factorial(N).divide(factorial(N-M).multiply(factorial(M))));
    }

    private static BigInteger factorial(int n) {
        BigInteger fac = BigInteger.valueOf(n);
        for (int i = 2; i < n; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        return fac;
    }
}
