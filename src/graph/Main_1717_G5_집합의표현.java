package graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_G5_집합의표현 {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        /*
         * 배열 초기화
         */
        parent = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (a == 0) union(x, y);
            else System.out.println((check(x, y)) ? "YES" : "NO");
        }

    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        /*
         * 부모가 더 작아야 한다는 조건이 없으므로
         * 대소 비교하지 않아도 된다
         */
        if (x != y) parent[y] = x;
//		if (x <= y) {
//			parent[y] = x;
//		} else {
//			parent[x] = y;
//		}
    }

    public static boolean check(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        /*
         * 갱신
         *
         * 최상위 값을 가지고 다시 복귀했을 때 최상위 부모를 저장하여
         * 다시 x의 부모를 찾을 때 똑같이 부모를 찾아 올라가는 중복을 방지
         */
        return parent[x] = find(parent[x]);
    }


}
