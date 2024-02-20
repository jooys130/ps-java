package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2252_G3_줄세우기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        /*
         * 메모리 초과 => 배열을 ArrayList로 동적할당
         * N = 32 * 10 ^ 3  / M = 10 ^ 10
         * 인접배열로 하면 32000 * 32000
         */

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<Integer>());
        }
        int[] cnt = new int[N]; // 진입 차수
        Queue<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            cnt[to]++;
            list.get(from).add(to);
        }

        for (int i = 0; i < N; i++) {
            if (cnt[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur+1).append(" ");
            for (int i = 0; i < list.get(cur).size(); i++) {
                int adj = list.get(cur).get(i);
                cnt[adj]--;
                if (cnt[adj] == 0) {
                    q.add(adj);
                }
            }
        }

        System.out.println(sb);

    }

}
