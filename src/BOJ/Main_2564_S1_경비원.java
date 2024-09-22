package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2564_S1_경비원 {
    static int row, col, N;
    static int[] stores;
    static int target;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        stores = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            // 1이면 북, 2는 남, 3은 서, 4는 동
            int num = Integer.parseInt(st.nextToken());
            stores[i] = getValue(dir, num);
        }
        st = new StringTokenizer(br.readLine());
        target = getValue(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 0; i < N; i++) {
            int tmp = Math.abs(target - stores[i]);
            ans += Math.min(tmp, 2 * (row + col) - tmp);
        }

        System.out.println(ans);
    }
    private static int getValue(int dir, int num) {
        if (dir == 1) {
            return num;
        } else if (dir == 2) {
            return row + 2 * col - num;
        } else if (dir == 3) {
            return 2 * (row + col) - num;
        } else{
            return col + num;
        }
    }
}
