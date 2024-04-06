package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10974_S3_모든순열 {
    static int N;
    static int[] nums;
    static boolean[] visited; // 중복 검사
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        visited = new boolean[N];
        permutation(0);
    }
    public static void permutation(int idx) {
        if (idx == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            nums[idx] = i+1;
            permutation(idx+1);
            visited[i] = false;
        }
    }
}
