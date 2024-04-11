package PRO;

import java.util.*;
class Solution_Lv2_소수찾기 {
    static int N;
    static Set<Integer> set;
    static boolean[] visited;
    static int ans;
    public int solution(String numbers) {
        N = numbers.length();
        set = new HashSet<>(); // 중복 제거
        visited = new boolean[N];
        permu(0, "", numbers);
        for (Integer num : set) {
            // System.out.println(num);
            if (isPrime(num)) ans++;
        }
        return ans;
    }
    public static void permu(int idx, String tmp, String numbers) {
        if (idx == N) return;
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            set.add(Integer.parseInt(tmp + numbers.charAt(i))); // (위치)
            permu(idx+1, tmp + numbers.charAt(i), numbers);
            visited[i] = false;
        }
    }
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i < (int) Math.sqrt(num) + 1; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
