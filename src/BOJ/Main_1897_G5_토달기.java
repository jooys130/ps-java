package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1897_G5_토달기 {
    static int N;
    static String init;
    static String[] words;
    static boolean[] visited;
    static String ans;
    static Queue<String> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        init = st.nextToken();
        // 주어진 단어
        words = new String[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            if (q.isEmpty() && words[i].equals(init)) {
                visited[i] = true;
                q.add(words[i]);
            }
        }
        bfs();
        System.out.println(ans);
    }
    private static void bfs() {
        while (!q.isEmpty()) {
            String cur = q.poll();
            ans = cur;
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                if (check(cur, words[i])) {
                    q.add(words[i]);
                    visited[i] = true;
                }
            }
        }
    }
    private static boolean check(String sh, String lon) {
        if (lon.length() - sh.length() != 1) return false;
        int idx = 0;
        for (int i = 0; i < sh.length(); i++) {
            while(idx < lon.length() && sh.charAt(i) != lon.charAt(idx)) {
                idx++;
            }
            if (idx == lon.length()) return false;
            idx++;
        }
        return true;
    }
}
