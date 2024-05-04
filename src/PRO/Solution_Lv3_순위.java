package PRO;

import java.util.*;
class Solution_Lv3_순위 {
    // 순위를 구하는 과정
    // = 각 노드에 대해 최단 거리 탐색
    // = 경유지를 거쳐서 갈 수 있다면 항상 가능한 것
    static int[][] map;
    public int solution(int n, int[][] results) {
        map = new int[n][n];
        // 초기 설정
        for (int i = 0; i < results.length; i++) {
            int winIdx = results[i][0]-1;
            int loseIdx = results[i][1]-1;
            map[winIdx][loseIdx] = 1;
        }
        // 경 출 도
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1; // 이긴 것만 저장
                    }
                }
            }
        }

        // 등수를 판별할 수 있는지 => 이긴 횟수 = n-1
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 || map[j][i] == 1) {
                    count++;
                }
            }
            if (count == n-1) answer++;
        }
        return answer;
    }
}