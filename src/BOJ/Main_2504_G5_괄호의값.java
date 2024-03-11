package BOJ;


import java.io.*;
import java.util.*;

public class Main_2504_G5_괄호의값 {
    // 분배법칙
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> q = new Stack<>();
        char[] input = br.readLine().toCharArray();
        int ans = 0;
        int tmp = 1;
        boolean flag = true;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                q.add(input[i]);
                tmp *= 2;
            }
            else if (input[i] == '[') {
                q.add(input[i]);
                tmp *= 3;
            }
            else if (input[i] == ')') {
                if (q.isEmpty() || q.peek() != '(') {
                    flag = false;
                    break;
                }
                if (input[i-1] == '(') {
                    ans += tmp;
                }
                q.pop();
                tmp /= 2;
            } else if (input[i] == ']') {
                if (q.isEmpty() || q.peek() != '[') {
                    flag = false;
                    break;
                }
                if (input[i-1] == '[') {
                    ans += tmp;
                }
                q.pop();
                tmp /= 3;
            } else {
                flag = false;
                break;
            }
        }
        if (!flag || !q.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }
}