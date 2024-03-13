package BOJ;

import java.io.*;
import java.util.*;

public class Main_1043_G4_거짓말 {
    static int N, M;
    static int[] parents;
    static List<int[]> combi;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        make();

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        // 진실 아는 사람 0으로 값 변경
        for (int i = 0; i < p; i++) {
            int num = Integer.parseInt(st.nextToken());
            parents[num] = 0;
        }

        combi = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int[] group = new int[g];
            for (int j = 0; j < g; j++) {
                group[j] = Integer.parseInt(st.nextToken());
            }
            if (g >= 2) {
                for (int j = 0; j < g-1; j++) {
                    union(group[j], group[j+1]);
                }
            }
//            System.out.println(Arrays.toString(parents));
            combi.add(group);
        }

        int cnt = M;
        // 모든 콤비에 대해 거짓말 가능 여부 판단
        for (int[] group : combi) {
            boolean flag = false;
            for (int i = 0; i < group.length; i++) {
                if (find(group[i]) == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) cnt--;
        }
        System.out.println(cnt);
    }
    public static void make() {
        parents = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            parents[i] = i;
        }
    }
    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            // 작은 거로 업데이트: 진실 아는 사람이 0이라서
            if (rootA < rootB)
                parents[rootB] = rootA;
            else
                parents[rootA] = rootB;
        }
    }
    public static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
}
