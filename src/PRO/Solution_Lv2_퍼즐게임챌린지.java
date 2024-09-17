package PRO;

class Solution_Lv2_퍼즐게임챌린지 {
    public int solution(int[] diffs, int[] times, long limit) {
        // level의 최솟값은?
        int low = 1;
        int high = 1;
        for (int i = 0; i < diffs.length; i++) {
            high = Math.max(high, diffs[i]);
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            long sum = times[0];
            for (int i = 1; i < diffs.length; i++) {
                sum += times[i];
                if (diffs[i] > mid)
                    sum += (times[i-1] + times[i]) * (diffs[i] - mid);
            }
            if (sum > limit) low += 1;
            else {
                high -= 1;
            }
        }
        return low;
    }
}