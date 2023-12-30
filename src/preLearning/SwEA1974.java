package preLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SwEA1974 {

    // 스도쿠 검증: 배열로 중복 확인에서 합을 검증하는 방식으로 변경

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] puzzle = new int[9][9];
        for (int tc = 1; tc < T + 1; tc++) {
            for (int i = 0; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    puzzle[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = 1;

            // 가로 세로 탐색
            for (int row = 0; row < 9; row++) {
                int rowSum = 0;
                int colSum = 0;
                for (int col = 0; col < 9; col++) {
                    rowSum += puzzle[row][col];
                    colSum += puzzle[col][row];
                }
                if (rowSum != 45 || colSum != 45) {
                    answer = 0;
                    break;
                }
            }

            if (answer == 0) {
                System.out.println("#" + tc + " " + answer);
                continue;
            }

            // 3 x 3 탐색
            for (int row = 0; row < 9; row += 3) {
                for (int col = 0; col < 9; col += 3) {
                    int sum = 0;
                    for (int i = row; i < row + 3; i++) {
                        for (int j = col; j < col + 3; j++) {
                            sum += puzzle[i][j];
                        }
                    }
                    if (sum != 45) {
                        answer = 0;
                        break;
                    }
                }
                if (answer == 0) {
                    break;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

    }

}
