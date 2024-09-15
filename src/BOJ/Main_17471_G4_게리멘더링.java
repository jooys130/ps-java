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

    public static class Main_1647_G4_도시분할계획 {
        static int N, M;
        static List<Node>[] list;
        static class Node implements Comparable<Node> {
            int to;
            int w;
            public Node(int to, int w) {
                this.to = to;
                this.w = w;
            }
            @Override
            public int compareTo(Node o) {
                return this.w - o.w;
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            list = new ArrayList[N+1];
            for (int i = 0; i < N+1; i++) {
                list[i] = new ArrayList<>();
            }

            /*
                두 개로 나누는 조합을 구할 게 아니라
                가장 높은 가중치를 나누는 간선 하나를 삭제해주면 된다!
             */

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list[a].add(new Node(b, c));
                list[b].add(new Node(a, c));
            }

            System.out.println(prim(1)); // 1번 노드부터 탐색
        }

        public static int prim(int node) {
            Queue<Node> pq = new PriorityQueue<>();
            boolean[] visited = new boolean[N+1];
            pq.add(new Node(node, 0));

            int result = 0;
            int connect = 0;
            int maxW = 0; // 가장 가중치가 큰 값 저장하기 위함
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                int to = cur.to;
                int w = cur.w;
                if (visited[to]) continue;

                visited[to] = true;
                result += w;
                // 1이 최대일수도 있으니 미리 기록
                maxW = Math.max(maxW, w);
                // N개 모두 탐색
                if (++connect == N) break;

                for (Node n : list[to]) {
                    if (!visited[n.to]) pq.add(n);
                }
            }
            return result - maxW;
        }
    }

    public static class Main_1717_G5_집합의표현 {
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

    public static class Main_1753_G4_최단경로 {

        static int V, E;
        static int INF = Integer.MAX_VALUE;
        static List<Node>[] list;
        static int[] minCost;
        static boolean[] visited;

        static class Node implements Comparable<Node> {

            int to;
            int cost;

            Node(int to, int cost) {
                this.to = to;
                this.cost = cost;
            }

            @Override
            public int compareTo(Node o) {
                return this.cost - o.cost;
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(br.readLine());
            list = new List[V + 1];
            for (int i = 1; i <= V; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list[from].add(new Node(to, cost)); // 방향 그래프
            }

            visited = new boolean[V + 1];
            minCost = new int[V + 1];
            Arrays.fill(minCost, INF);

            Queue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(start, 0));

            minCost[start] = 0;
            visited[start] = true;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                for (int i = 0; i < list[cur.to].size(); i++) {
                    int next = list[cur.to].get(i).to;
                    int nextCost = list[cur.to].get(i).cost;
                    if (!visited[next] && cur.cost + nextCost < minCost[next]) {
                        minCost[next] = cur.cost + nextCost;
                        pq.add(new Node(next, minCost[next]));
                    }
                }
                visited[cur.to] = true;
            }

            for (int i = 1; i <= V; i++) {
                System.out.println(minCost[i] == INF ? "INF" : minCost[i]);
            }
        }
    }

    public static class Main_2606_S3_바이러스 {
        static int N, E;
        // 인접 행렬 대신 인접 리스트를 사용하여 최적화 시도
        static List<List<Integer>> board;
        static boolean[] visited;
        static int ans;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            E = Integer.parseInt(br.readLine());
            board = new ArrayList<List<Integer>>();
            for (int i = 0; i < N; i++) {
                board.add(new ArrayList<Integer>());
            }

            visited = new boolean[N];
            for (int i = 0; i < E; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                board.get(from).add(to);
                board.get(to).add(from);
            }

            bfs(0);
            System.out.println(ans);
        }

        public static void bfs(int node) {
            Deque<Integer> q = new ArrayDeque<Integer>();
            for (int i = 0; i < board.get(node).size(); i++) {
                q.add(board.get(node).get(i));
            }
            visited[node] = true;

            while(!q.isEmpty()) {
                int cur = q.poll();
                if (!visited[cur]) {
                    for (int i = 0; i < board.get(cur).size(); i++) {
                        q.add(board.get(cur).get(i));
                    }
                    visited[cur] = true;
                    ans++;
                }
            }
        }
    }
}