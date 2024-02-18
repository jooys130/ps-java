package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1715_G4_카드정렬하기 {
    // 최소 힙 => 우선순위 큐
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            minHeap.add(Integer.parseInt(br.readLine()));
        }

        int ans = 0;
        while(minHeap.size() > 1) {
            int node = minHeap.poll() + minHeap.poll();
            minHeap.add(node);
            ans += node;
        }

        System.out.println(ans);
    }

}
