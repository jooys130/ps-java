package SSWAT;

import java.util.*;
import java.io.*;

public class Main_17140_G4_이차원배열과연산 {

    static int R, C, K;
    /*
        어떤 자료구조?
        크기가 정해져 있고 연산 후 나머지 자리에 0을 붙여야 하므로 100 * 100 만큼의 배열 할당
     */
    static int[][] map;
    static int RLEN, CLEN;
    static PriorityQueue<Info> pq;
    static class Info implements Comparable<Info> {
        int num;
        int cnt;
        Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info o) {
            // cnt -> num : 커지는 순으로
            if (this.cnt == o.cnt) {
                return this.num - o.num;
            }
            return this.cnt - o.cnt;
        }
    }

    public static void print() {
        for (int i = 0; i < RLEN; i++) {
            for (int j = 0; j < CLEN; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void solve() {
        int time = 0;
        // 조건에 맞게 정렬하기 위해
        pq = new PriorityQueue<>();
        while(true) {
            if (time > 100) {
                System.out.println(-1);
                return;
            }
            if (map[R][C] == K) {
                break;
            }
            if (RLEN >= CLEN) {
                R();
            } else {
                C();
            }
//            print();
//            System.out.println();
            time++;
        }
        System.out.println(time);
    }
    public static void R() {
        for (int i = 0; i < RLEN; i++) {
            // 행 별로 개수 구하기
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int j = 0; j < CLEN; j++) {
                if (map[i][j] == 0) continue;
                cnt.put(map[i][j], cnt.getOrDefault(map[i][j], 0) + 1);
            }
            // pq에서 정렬
            for (Integer key : cnt.keySet()) {
                pq.add(new Info(key, cnt.get(key)));
            }
            // 정렬 결과 반영
            int idx = 0;
            while(!pq.isEmpty()) {
                Info p = pq.poll();
                map[i][idx++] = p.num;
                map[i][idx++] = p.cnt;
            }
            // 크기 갱신
            CLEN = Math.max(idx, CLEN);
            if (CLEN >= 100) continue;

            // 뒤의 값 갱신 : 0으로 바꿔야 하는 경우 존재
            while(idx <= 99) {
                map[i][idx++] = 0;
                map[i][idx++] = 0;
            }
        }
    }
    public static void C() {
        for (int i = 0; i < CLEN; i++) {
            // 열 별로 개수 구하기
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int j = 0; j < RLEN; j++) {
                if (map[j][i] == 0) continue;
                cnt.put(map[j][i], cnt.getOrDefault(map[j][i], 0) + 1);
            }
            // pq에서 정렬
            for (Integer key : cnt.keySet()) {
                pq.add(new Info(key, cnt.get(key)));
            }
            // 정렬 결과 반영
            int idx = 0;
            while(!pq.isEmpty()) {
                Info p = pq.poll();
                map[idx++][i] = p.num;
                map[idx++][i] = p.cnt;
            }
            // 크기 갱신
            RLEN = Math.max(idx, RLEN);
            // 100 넘어가면 버린다
            if (RLEN >= 100) continue;

            // 뒤의 값 갱신 : 0으로 바꿔야 하는 경우 존재
            while(idx <= 99) {
                map[idx++][i] = 0;
                map[idx++][i] = 0;
            }
        }
    }
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());
        map = new int[100][100];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        RLEN = 3;
        CLEN = 3;
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
