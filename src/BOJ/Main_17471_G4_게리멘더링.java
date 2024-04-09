package BOJ;

import java.util.*;
import java.io.*;

public class Main_17471_G4_게리멘더링 {
    static int N; // 구역의 개수
    static int[] people; // 구역의 인구 수, idx: 구역 num
    static List<Integer> A; // 부분집합 1
    static List<Integer>[] mapping; // 연결 그래프 정보
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        mapping = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            mapping[i] = new ArrayList<>();
        }
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                mapping[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        ans = Integer.MAX_VALUE;
        A = new ArrayList<>(); // A와 B 집합으로 나눔
        combination(0, 1);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
    public static void combination(int idx, int start) {
        if (!A.isEmpty()) check();
        for (int i = start; i < N; i++) {
            A.add(i);
            combination(idx+1, i+1);
            A.remove(A.size()-1);
        }
    }
    public static void check() {
        List<Integer> B = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (!A.contains(i)) B.add(i);
        }
        if (A.size() + B.size() != N) return;
        if (bfs(A) && bfs(B)) {
            ans = Math.min(ans, getDiff(B));
        }
    }
    public static int getDiff(List<Integer> B) {
        int sumA = 0; int sumB = 0;
        for (int a : A) {
            sumA += people[a];
        }
        for (int b : B) {
            sumB += people[b];
        }
        return Math.abs(sumA - sumB);
    }
    public static boolean bfs(List<Integer> subset) {

        boolean[] check = new boolean[N+1];
        
        Queue<Integer> q = new ArrayDeque<>();
        int s = subset.get(0);
        check[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            int p = q.poll();
            for (int next : mapping[p]) {
                if (subset.contains(next) && !check[next]) {
                    q.add(next);
                    check[next] = true;
                }
            }
        }
        // 확인
        for (int node : subset) {
            if (!check[node]) return false;
        }
        return true;
    }
}