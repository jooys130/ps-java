package PRO;

import java.util.*;
class Solution_PCCP2_신입사원교육 {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int N;
    public int solution(int[] ability, int number) {
        N = ability.length;
        for (int i = 0; i < N; i++) {
            pq.offer(ability[i]);
        }
        for (int i = 0; i < number; i++) {
            int a = pq.poll();
            int b = pq.poll();
            pq.offer(a + b);
            pq.offer(a + b);
        }
        int answer = 0;
        while (!pq.isEmpty()) {
            int a = pq.poll();
            answer+=a;
        }
        return answer;
    }
}