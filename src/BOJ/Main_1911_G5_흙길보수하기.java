package BOJ;

import java.io.*;
import java.util.*;

public class Main_1911_G5_흙길보수하기 {
    // sweep
    static int N, L;
    static class Pos implements Comparable<Pos> {
        int x; int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Pos o) {
            if (this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }
    static Queue<Pos> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new PriorityQueue<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map.add(new Pos(x, y));
        }

        int idx = 0; // 갱신할 널판지 현황
        int ans = 0; // 널판지 개수
        while(!map.isEmpty()) {
            int start = map.peek().x;
            int end = map.peek().y;
            map.poll();

            if (idx < start) idx = start;
            else if(idx > end) continue;

            int len = end - idx;
            ans += len / L;
            idx = end;
            int remain = len % L;
            if (remain != 0) {
                ans++;
                idx += (L-remain); // 다음으로 덮을 곳
            }
        }
        System.out.println(ans);
    }
}
