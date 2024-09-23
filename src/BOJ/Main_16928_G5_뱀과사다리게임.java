package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_16928_G5_뱀과사다리게임 {
    static int N, M;
    static int[] board = new int[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x] = y;
        }

        int[] minCnt = new int[101];
        Arrays.fill(minCnt, Integer.MAX_VALUE);

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {1, 0}); // 1에서 시작
        minCnt[1] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == 100) {
                continue;
            }
            if (board[cur[0]] == 0) {
                for (int i = 1; i <= 6; i++) {
                    int next = cur[0] + i;
                    if (next > 100 || minCnt[next] <= cur[1] + 1) continue;
                    minCnt[next] = cur[1] + 1;
                    queue.offer(new int[] {next, cur[1] + 1});
                }
            } else {
                int next = board[cur[0]];
                if (minCnt[next] <= cur[1]) continue;
                minCnt[next] = cur[1];
                queue.offer(new int[] {next, cur[1]});
            }
        }
        System.out.println(minCnt[100]);
    }
}