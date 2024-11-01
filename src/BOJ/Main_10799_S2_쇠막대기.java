package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_10799_S2_쇠막대기 {
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < input.length(); i++) {
            char a = input.charAt(i);
            if (a == '(') {
                stack.push(i);
            } else if(a == ')') {
                stack.pop();
                if (input.charAt(i-1) == '(') {
                    ans += stack.size();
                } else if (input.charAt(i-1) == ')') {
                    ans += 1;
                }
            }
        }
        System.out.println(ans);
    }
}
