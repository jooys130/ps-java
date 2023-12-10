package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj14889 {

    static int N;
    static int minScore;
    static int[][] score;
    static ArrayList<Integer> teamA;
    static ArrayList<Integer> teamB;

    public static void main(String[] args) throws IOException {
        minScore = Integer.MAX_VALUE;

        // 팀 저장하는 변수 초기화
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        score = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0부터 teamNumber를 줄여나가면서
        int teamNumber = N / 2;
        recursive(0, teamNumber);
        System.out.println(minScore);

    }

    private static void recursive(int order, int teamNumber) {
        if (teamNumber == 0) { // A팀 구성이 끝나면
            // B팀 구성
            for (int i = 0; i < N; i++) {
                if (!teamA.contains(i)) {
                    teamB.add(i);
                }
            }
            checkScore();
            teamB.clear(); // 점수 계산 후 초기화
            return;
        }

        for (int i = order; i < N; i++) {
            teamA.add(i);
            recursive(i + 1, teamNumber - 1);
            teamA.remove(teamA.size() - 1); // 가장 마지막에 있는 거
        }
    }

    private static void checkScore() {
        int teamA_score = 0;
        int teamB_score = 0;

        // 0 - 123, 1 - 23, 2 - 3 ..
        for (int i = 0; i < N / 2 - 1; i++) {
            for (int j = i+1; j < N / 2; j++){
                int teamA_1 = teamA.get(i);
                int teamA_2 = teamA.get(j);
                teamA_score += score[teamA_1][teamA_2] + score[teamA_2][teamA_1];

                int teamB_1 = teamB.get(i);
                int teamB_2 = teamB.get(j);
                teamB_score += score[teamB_1][teamB_2] + score[teamB_2][teamB_1];
            }
        }
        int tmp_result = Math.abs(teamA_score - teamB_score);
        minScore = Math.min(minScore, tmp_result);
    }

}
