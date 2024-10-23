package PRO;

import java.util.*;
class Solution_Lv2_의상 {
    static HashMap<String, Integer> cMap;
    static int ans = 1;
    static int[] cnt;
    static boolean[] isSelected;
    public int solution(String[][] clothes) {
        cMap = new HashMap<>();
        for (String[] cloth : clothes) {
            // if (!cMap.containsKey(cloth[1])) {
            //     cMap.put(cloth[1], new ArrayList<>());
            //     cMap.get(cloth[1]).add(cloth[0]);
            // } else {
            //     cMap.get(cloth[1]).add(cloth[0]);
            // }
            cMap.put(cloth[1], cMap.getOrDefault(cloth[1], 0) + 1);
        }
        // 1번 씩 포함 (N+1) * (M+1) - 1: 하나도 포함 안 하는 경우 더하고 모두 포함 안하는 경우 1 빼기
        for (String key: cMap.keySet()) {
            ans *= (cMap.get(key) + 1);
        }
        return ans - 1;
    }
}
