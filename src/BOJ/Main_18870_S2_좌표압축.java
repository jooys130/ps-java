package BOJ;

import java.util.*;
import java.io.*;

public class Main_18870_S2_좌표압축 {


    // ranking
    static int N;
    static int[] X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = new int[N];
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        // ???? + set
        int[] arr = Arrays.copyOf(X, N);
        Arrays.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], idx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int key : X) {
            sb.append(map.get(key)).append(" ");
        }
        System.out.println(sb);
    }
}
