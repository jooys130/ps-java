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

    public static class Main_1015_S4_수열정렬 {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] A = new int[N];
            int[] B = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                B[i] = A[i];
            }
            Arrays.sort(B);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (A[i] == B[j]) {
                        sb.append(j).append(" ");
                        B[j] = -1; // 사용한 곳 방문 처리
                        break;
                    }
                }
            }
            System.out.println(sb);
        }

    }

    public static class Main_1181_S5_단어정렬 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }
            // 정렬
            Arrays.sort(arr, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return (o1.length() == o2.length()) ? o1.compareTo(o2) : o1.length() - o2.length();
                }
            });

            StringBuilder sb = new StringBuilder();
            sb.append(arr[0]).append('\n');
            for (int i = 1; i < N; i++) {
                // 중복된 단어 제거
                if (arr[i].equals(arr[i-1])) {
                    continue;
                }
                sb.append(arr[i]).append('\n');
            }
            System.out.println(sb);
        }

    }

    public static class Main_1715_G4_카드정렬하기 {
        // 최소 힙 => 우선순위 큐
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                minHeap.add(Integer.parseInt(br.readLine()));
            }

            int ans = 0;
            while(minHeap.size() > 1) {
                int node = minHeap.poll() + minHeap.poll();
                minHeap.add(node);
                ans += node;
            }

            System.out.println(ans);
        }

    }
}
