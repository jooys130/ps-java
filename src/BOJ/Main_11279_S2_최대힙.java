package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_11279_S2_최대힙 {
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(br.readLine());
            if(k == 0) {
                System.out.println(pq.isEmpty()? 0: pq.poll());
            } else {
                pq.add(k);
            }
        }
    }
}
