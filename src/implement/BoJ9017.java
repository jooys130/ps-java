package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BoJ9017 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            // 각 팀별 개수 세기 (index = 팀 번호)
            int[] cnt = new int[N + 1];
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int i = 0; i < N; i++) {
                int teamNumber = Integer.parseInt(st.nextToken());
                cnt[teamNumber] += 1;
            }

            // teamNumber : [ranks ..]
            HashMap<Integer, List<Integer>> hashMap = new HashMap();
            st = new StringTokenizer(line);

            // 등수 매기기
            int rank = 1;
            for (int i = 0; i < N; i++) {
                int teamNumber = Integer.parseInt(st.nextToken());
                if (cnt[teamNumber] == 6) {
                    if (!hashMap.containsKey(teamNumber)) {
                        hashMap.put(teamNumber, new ArrayList<>());
                    }

                    if (hashMap.get(teamNumber).size() < 5) {
                        hashMap.get(teamNumber).add(rank);
                    }

                    rank++;
                }
            }

            // 우승자 선별
            int winnerNumber = 0;
            int minSum = Integer.MAX_VALUE;
            int fifthRank = N;
            for (Integer key : hashMap.keySet()) {
                List<Integer> ranks = hashMap.get(key);

                // team 별 4번째 등수까지의 합 구하기
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += ranks.get(k);
                }

                if (sum < minSum) { // 4번째 합으로 우승자가 결정되는 경우
                    winnerNumber = key;
                    fifthRank = ranks.get(4);
                    minSum = sum;
                } else if (sum == minSum) { // 같은 경우
                    if (fifthRank > ranks.get(4)) { // 5번째 등수 비교
                        // 해당 팀이 더 작은 경우 갱신
                        winnerNumber = key;
                        fifthRank = ranks.get(4);
                    }
                }
            }

            System.out.println(winnerNumber);

        }
    }

}
