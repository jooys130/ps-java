package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_G5_신기한소수 {

    /*
     * 수행 시간:   124ms
     * 메모리:	14164KB
     * 푼 방법: 	부분 집합 + 소수 판별
     */

    static int N;
    static int[] primes = {2, 3, 5, 7};
    static int[] odds = {1, 3, 7, 9}; 	// 5를 제외한 홀수
    static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 첫 번째 자리
        for (int prime: primes) {
            recur(prime, 1);
        }
    }

    private static void recur(int number, int count) {
        if (count == N) {
            System.out.println(number);
            return;
        }

        for (int odd : odds) {
            int nextNumber = number * 10 + odd;
            if (isPrime(nextNumber)) {
                recur(nextNumber, count + 1);
            }
        }
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

}
