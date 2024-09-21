package PRO;

import java.util.*;
class Solution_Lv3_단어변환 {
    static Queue<String> tmp;
    static int LEN;
    static boolean[] visited;
    static int answer = 0;
    static boolean flag = false;
    public int solution(String begin, String target, String[] words) {
        LEN = begin.length();
        tmp = new ArrayDeque<>();
        visited = new boolean[words.length];
        // target이 words에 있는지 확인
        for (int i = 0; i < words.length; i++) {
            if (countOfSame(target, words[i]) == LEN) flag = true;
        }
        if (!flag) return answer;
        tmp.add(begin);
        solve(target, words);
        return answer;
    }
    public void solve(String target, String[] words) {
        while(!tmp.isEmpty()) {
            String next = tmp.poll();
            if (next.equals(target)) {
                break;
            }
            for (int i = 0; i < LEN; i++) {
                if (!visited[i] && countOfSame(next, words[i]) == LEN-1) {
                    visited[i] = true;
                    tmp.add(words[i]);
                }
            }
            answer++;
        }
    }
    public int countOfSame (String a, String b) {
        int cnt = 0;
        for (int i = 0; i < LEN; i++) {
            if (a.charAt(i) == b.charAt(i)) cnt++; // charAt
        }
        return cnt;
    }
}