package PRO;

import java.util.*;
class Solution_Lv2_연속부분수열합의개수 {
    static int N;
    static Set<Integer> set = new HashSet<>();
    public int solution(int[] elements) {
        N = elements.length;
        for (int len = 1; len <= N; len++) {
            combi(elements, len);
        }
        return set.size();
    }
    public void combi(int[] elements, int len) {
        for (int i = 0; i < N + len; i++) {
            int sum = 0;
            for (int j = i; j < i + len; j++) {
                sum += elements[j%N];
            }
            set.add(sum);
        }
    }
}