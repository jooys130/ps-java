package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1182_S2_부분수열의합 {
    static int[] arr;
    static boolean[] visited;
    static int N, S;
    // N개의 원소를 가지는 arr의 부분수열 중 모든 원소의 합이 S가 되는 경우의 수는?
    // 전부 선택하지 않은 경우(공집합) 고려 안 해서 '틀렸습니다'
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 각 원소를 선택 혹은 비선택
        dfs1(0, 0);
        // dfs2(0);
        System.out.println(S == 0 ? ans-1: ans);
    }
    public static void dfs1(int idx, int sum) {
        if (idx == N) {
            if (sum == S) ans++;
            return;
        }
        dfs1(idx+1, sum + arr[idx]);
        dfs1(idx+1, sum);
    }
    public static void dfs2(int idx) {
        // flag 사용
        if (idx == N) {
            int sum = 0; int cnt = 0;
            for (int i = 0; i < N; i++) {
                if(visited[i]) {
                    sum += arr[i];
                    cnt++;
                }
            }
            if (sum == S && cnt > 0) {
                ans++;
            }
            return;
        }
        visited[idx] = true;
        dfs2(idx+1);
        visited[idx] = false;
        dfs2(idx+1);
    }
}