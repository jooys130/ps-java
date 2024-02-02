package Algo.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_1522_문자열교환 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int N = input.length();

        int aCount = 0;
        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == 'a') {
                aCount++;
            }
        }

        int answer = 1000;
        for (int i = 0; i < N; i++) {
            int bCount = 0;
            for (int j = 0; j < aCount; j++) {
                if (input.charAt((i + j)%N) == 'b') {
                    bCount++;
                }
            }
            answer = Math.min(answer, bCount);
        }
        System.out.println(answer);
    }
}
