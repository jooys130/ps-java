package BOJ;

import java.io.*;
import java.util.*;

public class Main_9935_G4_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String regex = br.readLine();
        int regexSize = regex.length();
        // regex가 line보다 길 때 예외처리 안 해서 ArrayIndexOutOfBounds => #16 수정
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            stack.push(line.charAt(i));
            if (stack.size() >= regexSize){
            // System.out.println(stack.get(stack.size()-1) + " " + regex.charAt(regexSize-1));
                boolean flag = true;
                for (int j = 0; j < regexSize; j++) {
                    if (stack.get(stack.size()-regexSize+j) != regex.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < regexSize; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
