package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20125_S4_쿠키의신체측정 {
    static int N;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        boolean isFirst = true;
        int[] head = new int[2];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = tmp.charAt(j);
                if (arr[i][j] == '*' && isFirst) {
                    head[0] = i + 1; head[1] = j;
                    isFirst = false;
                }
            }
        }
        System.out.println((head[0]+1) + " " + (head[1]+1));
        int leftArm = -1; int rightArm = -1;
        int x = head[0]; int y = head[1];
        while (!outOfRange(x, y) && arr[x][y] == '*') {
            leftArm++;
            y--;
        }
        y = head[1];
        while (!outOfRange(x, y) && arr[x][y] == '*') {
            rightArm++;
            y++;
        }
        y = head[1];
        int middle = -1; int leftLeg = 0; int rightLeg = 0;
        while (!outOfRange(x, y) && arr[x][y] == '*') {
            middle++;
            x++;
        }
        int endX = x; int endY = y - 1; int endY2 = y + 1;
        while (!outOfRange(x, endY) && arr[x][endY] == '*') {
            leftLeg++;
            x++;
        }
        while (!outOfRange(endX, endY2) && arr[endX][endY2] == '*') {
            rightLeg++;
            endX++;
        }
        System.out.println(leftArm + " " + rightArm + " " + middle + " " + leftLeg + " " + rightLeg);
    }
    private static boolean outOfRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
