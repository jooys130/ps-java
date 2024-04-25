package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2655_G3_가장높은탑쌓기 {
    // 넓이와 무게가 작아지는 순으로 벽돌을 쌓을 수 있는데 이때 최대 높이를 가지는 경우는?
    static int N;
    static class Brick implements Comparable<Brick> {
        int num;
        int s, h, w; // 넓이, 높이, 무게
        Brick(int num, int s, int h, int w) {
            this.num = num;
            this.s = s;
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Brick o) {
            return o.s - this.s;
        }

        @Override
        public String toString() {
            return "Brick{" +
                    "num=" + num +
                    ", s=" + s +
                    ", h=" + h +
                    ", w=" + w +
                    '}';
        }
    }
    static List<Brick> bricks;
    static int[] dp; // 높이 저장
    static int[] idx; // 전 인덱스
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bricks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            bricks.add(new Brick(i+1, s, h, w));
        }

        Collections.sort(bricks);
        // System.out.println(bricks);

        dp = new int[N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            dp[i] = bricks.get(i).h;
            for (int j = 0; j < i; j++) {
                if (bricks.get(i).w < bricks.get(j).w) {
                    dp[i] = Math.max(dp[i], dp[j] + bricks.get(i).h);
                }
            }
            max = Math.max(max, dp[i]);
            // System.out.println(i + " " + Arrays.toString(dp));
        }
        // System.out.println(max);
        List<Integer> nums = new ArrayList<>();
        int k = N;
        while(k-- > 0) {
            if (max == dp[k]) {
                nums.add(bricks.get(k).num);
                max -= bricks.get(k).h;
            }
        }
        System.out.println(nums.size());
        for (int num : nums) System.out.println(num);
    }
}
