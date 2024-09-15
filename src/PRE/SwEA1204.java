package PRE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SwEA1204 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            String testCaseNumber = br.readLine();

            // 점수는 0점 이상 100점 이하의 값
            int[] scoreCount = new int[101];
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 1000명의 수학 성적
            for (int i = 0; i < 1000; i++) {
                int score = Integer.parseInt(st.nextToken());
                scoreCount[score] += 1;
            }

            int answer = 0;
            int maxCount = 0;
            for (int i = 0; i < 101; i++) {
                if (maxCount <= scoreCount[i]) {
                    maxCount = scoreCount[i];
                    answer = i;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
