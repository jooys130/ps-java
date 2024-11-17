package PRO;

import java.util.*;
class Solution_PCCP2_카페확장 {
    static Deque<Integer> q;
    static int ans;
    public int solution(int[] menu, int[] order, int k) {
        int time = 0;
        int i = 0;
        q = new ArrayDeque<>();
        while (true) {
            if (q.isEmpty() && i >= order.length) break;
            while (!q.isEmpty() && q.peek() <= time) {
                q.poll();
            }
            if (time % k == 0 && time < k * order.length) {
                if (q.isEmpty()) q.offer(time + menu[order[i++]]);
                else q.offer(q.peekLast() + menu[order[i++]]);
            }
            ans = Math.max(ans, q.size());
            time++;
        }
        return ans;
    }
}