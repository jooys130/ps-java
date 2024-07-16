package PRO;

// 간선 개수 1_000_000 너무 커서 인접리스트나 간선리스트가 아니라 "노드"를 기준으로 관리한다
// 이때 노드의 사이즈를 정할 수 없으니 in과 out인 리스트를 생성한다 <= map 사용
/*
    생성 노드: in 0, out 2 이상
    도넛 모양: in 1, out 1 (모든 노드 => 값이 쌓이게 됨)
    막대 모양: in 1, out 0 (마지막 노드)
    8자 모양: in 2 이상, out 2 (중간 노드)
*/
import java.util.*;
class Solution_Lv2_도넛과막대그래프 {
    static HashMap<Integer, Integer> in = new HashMap<>();
    static HashMap<Integer, Integer> out = new HashMap<>();
    static int[] answer = new int[4]; // 도넛, 막대, 8자 모양
    public int[] solution(int[][] edges) {
        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }
        for (int key : out.keySet()) {
            if (out.get(key) >= 2) {
                if (!in.containsKey(key)) {
                    answer[0] = key;
                }
                else {
                    answer[3] += 1;
                }
            }
        }
        for (int key: in.keySet()) {
            if (in.get(key) >= 1) {
                if (!out.containsKey(key)) {
                    answer[2] += 1;
                }
            }
        }
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
}