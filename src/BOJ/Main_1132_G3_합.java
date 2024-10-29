package BOJ;

import java.io.*;
import java.util.*;

public class Main_1132_G3_합 {
    static int N;
    static class Node implements Comparable<Node> {
        char bet;
        long size;
        Node(char bet, int size) {
            this.bet = bet;
            this.size = size;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.size, o.size);
        }
    }
    static Node[] alpha = new Node[10];
    static List<String> list = new ArrayList<>();
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] number = new int[10];
        Arrays.fill(number, -1);
        for (int i = 0; i < 10; i++) {
            alpha[i] = new Node((char) ('A' + i), 0);
        }
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            list.add(input);
            for (int j = 0; j < input.length(); j++) {
                char chr = input.charAt(j);
                int tmp = input.length() - j - 1;
                alpha[chr - 'A'].size += (long) Math.pow(10, tmp);
            }
        }

        // 0이 안 되는 거 체크
        Set<Character> noZero = new HashSet<>();
        for (String str : list) {
            noZero.add(str.charAt(0));
        }

        // 알파벳에 따른 숫자 할당
        Arrays.sort(alpha); // 오름차순 정렬
        // 가득 찼을 경우 0 먼저 지정
        boolean isFull = true;
        for (int i = 0; i < 10; i++) {
            if (alpha[i].size == 0) {
                isFull = false;
                break;
            }
        }
        if (isFull) {
            for (Node n : alpha) {
                if (!noZero.contains(n.bet)) {
                    number[n.bet - 'A'] = 0;
                    break;
                }
            }
        }
        int num = 9;
        for (int i = alpha.length - 1; i >= 0; i--) {
            if (number[alpha[i].bet - 'A'] == 0) continue;
            number[alpha[i].bet - 'A'] = num--;
        }
        // System.out.println(Arrays.toString(number));
        for (int i = 0; i < N; i++) {
            String tmp = list.get(i);
            for (int j = 0; j < tmp.length(); j++) {
                char chr = tmp.charAt(j);
                ans += (long) (number[chr - 'A'] * Math.pow(10, tmp.length() - j - 1));
            }
        }
        System.out.println(ans);
    }
}