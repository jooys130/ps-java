package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15961_G4_회전초밥 {
    /*
     * 시간: 548 ms
     * 메모리: 170892 kb
     */
    static int N, d, k, c;
    static int[] map;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        int[] sushi = new int[d+1];
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (sushi[map[i]] == 0) cnt++;
            sushi[map[i]]++;
        }
        ans = cnt;
        for (int i = 1; i <= N; i++) {
            if (ans <= cnt) {
                if (sushi[c] == 0) ans = cnt+1;
                else ans = cnt;
            }

            if (i == N) break;

            sushi[map[i-1]]--;
            if (sushi[map[i-1]] == 0) cnt--;

            if(sushi[map[(i+k-1)%N]] == 0) cnt++;
            sushi[map[(i+k-1)%N]]++;
        }
        System.out.println(ans);
    }
}
