package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1976_G4_여행가자 {
    static int N; // 도시의 수
    static int M; // 여행에 속한 도시들 수
    static int[] parents;
    static int[] plan; // 여행 때 갈 도시들
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        init();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int con = Integer.parseInt(st.nextToken());
                if (con == 1) {
                    union(i, j);
                }
            }
        }
        // System.out.println(Arrays.toString(parents));
        plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        boolean flag = true;
        for (int i = 0; i < M-1; i++) {
            int aRoot = find(plan[i]);
            int bRoot = find(plan[i+1]);
            if (aRoot != bRoot) flag = false;
        }
        System.out.println(flag ? "YES" : "NO");
    }
    public static void init() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }
    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return;
        if (aRoot > bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
    }
    public static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
}
