package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486_D4_장훈이의높은선반 {
    // 점원들의 키의 합이 B 이상인 경우를 구한다
    // 이미 합이 최솟값을 넘는다면 가지치기
    static int[] clerk;
    static int N, B; // N명의 점원, 기준 선반 높이 B
    static int minH; // B 이상의 높이 중 최솟값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            clerk = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                clerk[i] = Integer.parseInt(st.nextToken());
            }

            minH = Integer.MAX_VALUE;
            solve(0, 0);
            sb.append("#").append(tc).append(" ")
                    .append(minH-B).append("\n");
        }
        System.out.println(sb);
    }
    public static void solve(int h, int idx) {
        // 쌓은 높이와 점원의 인덱스를 인자로 받는다
        // 해당 점원을 쓰냐 혹은 안 쓰냐 => 부분집합
        if (h > minH) return;
        if (idx >= N) {
            if (h >= B)
                // 끝까지 다 보면 정답 갱신 후 리턴
                minH = Math.min(minH, h);
            return;
        }
        solve(h + clerk[idx], idx+1);
        solve(h, idx+1);
    }
}
