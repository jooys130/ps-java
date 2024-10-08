package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11505_G1_구간곱구하기 {

    static long[] input;
    static long[] tree;
    static long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        input = new long[N];
        tree = new long[N << 2];

        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(br.readLine());
        }
        init(0, N - 1, 1);
        // System.out.println(Arrays.toString(tree));

        StringBuilder sb = new StringBuilder(K * 3);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                input[b-1] = c;
                update(0, N - 1, 1, b - 1, c);
                // System.out.println("1 - " + Arrays.toString(tree));
            } else if (a == 2) {
                sb.append(multi(0, N - 1, 1, b - 1, c - 1) + "\n");
                // System.out.println("2 - " + Arrays.toString(tree));
            } else {
                return;
            }
        }
        System.out.println(sb);
    }

    public static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = input[start];
        }

        int mid = (start + end) >> 1;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }

    public static long multi(int start, int end, int node, int left, long right) {
        if (left > end || right < start) return 1;

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) >> 1;
        return (multi(start, mid, node * 2, left, right)
            * multi(mid + 1, end, node * 2 + 1, left, right)) % MOD;

    }

    public static long update(int start, int end, int node, int index, long diff) {
        // 곱을 업데이트 해줄 때는 구간 합처럼 위에서부터 차이를 연산하며 내려가면 안 된다
        // 즉 bottom-up으로 !!

        if (index < start || index > end) { // 범위 밖이면
            // System.out.println("OOR " + start + " " + end + " " + index + " " + node);
            return tree[node];
        }
        if (start == end) {
            // System.out.println("leaf " + node + "인덱스의 " + tree[node] + "값을 " + diff + "로 바꾼다");
            return tree[node] = diff;
        }

        int mid = (start + end) >> 1;
        // System.out.println("main " + mid);
        return tree[node] = (update(start, mid, node * 2, index, diff)
            * update(mid + 1, end, node * 2 + 1, index, diff)) % MOD;

    }

}
