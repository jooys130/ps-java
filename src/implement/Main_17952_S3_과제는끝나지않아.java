package implement;

import java.util.*;
import java.io.*;

public class Main_17952_S3_과제는끝나지않아 {
    static Deque<int[]> stack;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        // 자료구조 stack 활용
        stack = new ArrayDeque<int[]>();
        for (int i = 0; i < C; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int size = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                if (time == 1) {
                    ans += size;
                } else {
                    stack.addLast(new int[] {size, time-1});
                }
            } else {
                // 이전 작업 수행
                if (!stack.isEmpty()) {
                    int[] cur = stack.pollLast();
                    if (cur[1] == 1) {
                        ans += cur[0];
                    } else {
                        stack.addLast(new int[] {cur[0], cur[1] - 1});
                    }
                }
            }
        }
        System.out.println(ans);
    }
}