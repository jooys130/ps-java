package PRO;

import java.util.*;
class Solution_Lv3_네트워크 {
    // union find 또는 그래프
    static int[] parents;
    public int solution(int n, int[][] computers) {
        parents = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    union(i+1, j+1);
                }
            }
        }
        System.out.println(Arrays.toString(parents));
        Set<Integer> s = new HashSet<>();
        for (int i = 1; i < n+1; i++) {
            parents[i] = find(parents[i]); // !!!!
            s.add(parents[i]);
        }
        return s.size();
    }
    public void union(int i, int j) {
        int a = find(i);
        int b = find(j);
        if (a == b) return;
        if (a > b) parents[a] = b; // 작은 값을 값으로
        else parents[b] = a;
    }
    public int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
}