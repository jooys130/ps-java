package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5432_D4_쇠막대기자르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            char[] input = br.readLine().toCharArray();
            Queue<Character> stack = new ArrayDeque<>();
            int ans = 0;
            for (int i = 0; i < input.length; i++) {
                if (input[i] == '(') {
                    stack.add(input[i]);
                } else {
                    stack.remove();
                    if (input[i-1] == '(') {
                        ans += stack.size();
                    } else {
                        ans += 1;
                    }
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}
