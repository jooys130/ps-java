package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1553_G5_도미노찾기 {
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] domino;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[8][7];
        visited = new boolean[8][7];
        domino = new boolean[7][7]; // 우측 삼각형만 사용
        for (int i = 0; i < 8; i++) {
            String input = br.readLine();
            for (int j = 0; j < 7; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        solve(0, 0);
        System.out.println(cnt);
    }
    public static void solve(int x, int y) {
        if (x >= 8) {
            cnt++;
            return;
        }
        if (y >= 7) {
            solve(x + 1, 0);
            return;
        }
        if (visited[x][y]) {
            solve(x, y+1);
            return;
        }
        // 오른쪽
        if (inRange(x, y+1) &&  !visited[x][y+1]) {
            int num1 = map[x][y];
            int num2 = map[x][y+1];
            if (!domino[num1][num2] && !domino[num2][num1]) {
                domino[num1][num2] = true;
                visited[x][y] = true;
                visited[x][y+1] = true;
                solve(x, y+2);
                domino[num1][num2] = false;
                visited[x][y] = false;
                visited[x][y+1] = false;
            }
        }
        // 아래
        if (inRange(x + 1, y) &&  !visited[x+1][y]) {
            int num1 = map[x][y];
            int num2 = map[x+1][y];
            if (!domino[num1][num2] && !domino[num2][num1]) {
                domino[num1][num2] = true;
                visited[x][y] = true;
                visited[x+1][y] = true;
                solve(x, y+1);
                domino[num1][num2] = false;
                visited[x][y] = false;
                visited[x+1][y] = false;
            }
        }
    }

    private static boolean inRange(int x, int y){
        return x >= 0 && x < 8 && y >= 0 && y < 7;
    }
}