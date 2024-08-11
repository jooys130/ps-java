package PRO;

import java.util.*;
class Solutioin_Lv2_롤케이크자르기 {
    public int solution(int[] topping) {
        Map<Integer, Integer> a = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int answer = 0;
        // dictionary 초기화
        for (int t : topping) {
            a.put(t, a.getOrDefault(t, 0) + 1);
        }
        // set에 넣으면서 비교
        for (int t : topping) {
            a.put(t, a.get(t) - 1);
            set.add(t);
            if (a.get(t) == 0) {
                a.remove(t);
            }
            if (a.size() == set.size()) {
                answer++;
            }
        }
        return answer;
    }
}
