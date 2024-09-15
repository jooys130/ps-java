package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_S1_Z_주연수 {

    static int N, r, c;
    static int[][] map;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);
        // 몇 사분면인지 찾자
        find(r, c, size);
    }

    private static void find(int i, int j, int size) {
        if (size == 1) {
            System.out.println(ans);
            return;
        }
        int half = size / 2;
        if (i < half && j < half) {
            find(i, j, half);
        } else if (i < half && j >= half) {
            ans += half * half;
            find(i, j - half, half);
        } else if (i >= half && j < half) {
            ans += half * half * 2;
            find(i - half, j, half);
        } else {
            ans += half * half * 3;
            find(i - half, j - half, half);
        }
    }
}