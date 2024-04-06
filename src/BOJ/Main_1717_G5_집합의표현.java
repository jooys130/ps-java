package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_G5_집합의표현 {
    static int N, M; // 집합 원소 개수, 연산의 개수
    static int[] parents; // 부모 루트 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (oper == 0) {
                union(a, b);
            } else if (oper == 1) {
                int aRoot = find(a);
                int bRoot = find(b);
                if (aRoot == bRoot) System.out.println("YES");
                else System.out.println("NO");
            }
            // ßSystem.out.println(Arrays.toString(parents));
        }
    }
    public static void init() {
        parents = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            parents[i] = i;
        }
    }
    public static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return;
        if (aRoot > bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
    }
}
