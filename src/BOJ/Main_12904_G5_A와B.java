package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12904_G5_A와B {
    // S를 T로 만들기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        /*
            T에서 제거
            두 가지 방법이 사실은 1가지 방향 => O(T-S)

            String => substring + transform sb + reverse
            StringBuilder => deleteCharAt + reverse
         */
        while (T.length() > S.length()) {
            if (T.charAt(T.length()-1) == 'A') {
                T.deleteCharAt(T.length()-1);
            } else if (T.charAt(T.length()-1) == 'B') {
                T.deleteCharAt(T.length()-1);
                T.reverse();
            }
        }

        System.out.println((T.compareTo(S) == 0) ?1:0);
    }
}
