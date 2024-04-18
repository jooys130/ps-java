package PRO;

import java.util.*;
class Solution_Lv2_연속된부분수열의합 {
    static int N;
    static int start, end;
    static int maxSum;
    static List<Candi> candis;
    static class Candi implements Comparable<Candi> {
        int start, end, len;
        Candi(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }
        @Override
        public int compareTo(Candi c) {
            if (c.len == this.len) {
                return this.start - c.start;
            }
            return this.len - c.len;
        }
        @Override
        public String toString() {
            return start + " " + end + " " + len;
        }
    }
    public int[] solution(int[] sequence, int k) {
        N = sequence.length;
        maxSum = sequence[0];
        candis = new ArrayList<>();
        while (start < N && end < N) {
            if (maxSum == k) {
                candis.add(new Candi(start, end, end-start));
            }
            if (maxSum <= k) {
                end++;
                if (end < N) maxSum += sequence[end];
            } else {
                maxSum -= sequence[start];
                start++;
            }
        }
        Collections.sort(candis);
        // System.out.println(candis);
        return new int[] {candis.get(0).start, candis.get(0).end};
    }
}
