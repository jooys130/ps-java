package BOJ;

import java.util.*;
import java.io.*;

public class Main_20922_S1_겹치는건싫어 {
    // K개 이하로 포함된 최장 연속 부분 수열의 길이를 구하여라
    static int N, K;
    static int[] a;
    /*
        개수를 어떻게 기억하지? -> count 배열
        위치를 어떻게 기억하지? -> 투 포인터
     */
    static int[] count;
    static int start, end;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N];
        count = new int[100_001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        // O(N)
        while (start < N) {
            int num = a[start];
            if (count[num] < K) {
                count[num]++;
                start++;
            } else {
                count[a[end]]--;
                end++;
            }
            answer = Math.max(answer, start - end);
            // System.out.println(start + " " + end + " " + answer);
            // System.out.println(num + " " + count[num]);
        }
        System.out.println(answer);
    }

    public static class Main_14500_G4_테트로미노 {

        static int N, M;
        static int[][] map;
        static boolean[][] visited;
        static int[] dx = {0, 0, -1, 1};
        static int[] dy = {-1, 1, 0, 0};
        static int ans;
        static int MAX = 0; // 가지치기 위해 필요

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > MAX) {
                        MAX = map[i][j];
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    visited[i][j] = true;
                    dfs(i, j, 1, map[i][j]);
                    visited[i][j] = false;
                }
            }
            System.out.println(ans);
        }

        public static void dfs(int x, int y, int cnt, int sum) {
            // 가지치기
            if (sum + (4 - cnt) * MAX <= ans) {
                return;
            }
            // 임계 조건
            if (cnt == 4) {
                ans = Math.max(ans, sum);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }
                if (cnt == 2) {
                    visited[nx][ny] = true;
                    dfs(x, y, cnt + 1, sum + map[nx][ny]);
                    visited[nx][ny] = false;
                }
                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    public static class Main_14501_S3_퇴사 {

        static int N;
        static int[] T; // 상담 기간
        static int[] P; // 상담 금액
        static int[] dp;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            T = new int[N];
            P = new int[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                T[i] = Integer.parseInt(st.nextToken());
                P[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[N + 1];

            for (int i = 0; i < N; i++) {
                if (i + T[i] <= N) { // 상담이 가능한 경우
                    // i번째 상담을 진행했을 때
                    dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
                }
                // i번째 상담을 진행하지 않는 경우
                dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            }
            System.out.println(dp[N]);
        }
    }

    public static class Main_14502_G4_연구소 {

        static final int[] dx = {-1, 0, 1, 0};
        static final int[] dy = {0, -1, 0, 1};
        static class Pos {

            int x;
            int y;

            public Pos(int x, int y) {
                this.x = x;
                this.y = y;
            };
        }
        static int N, M;
        static int[][] map;
        static int answer = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0); // 벽 세우기

            System.out.println(answer);
        }

        public static void dfs(int wallCount) {
            if (wallCount == 3) {
                bfs();
                return;
            }

            for (int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        // 백트래킹
                        map[i][j] = 1;
                        dfs(wallCount + 1);
                        map[i][j] = 0;
                    }
                }
            }
        }

        public static void bfs() {
            // 바이러스 퍼트리기 위해 위치 저장
            Queue<Pos> virusPos = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2) {
                        virusPos.offer(new Pos(i, j));
                    }
                }
            }
            // 원본을 바꾸지 않기 위해 깊은 복사
            int copyMap[][] = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }
            // 바이러스 퍼트리기
            while (!virusPos.isEmpty()) {
                Pos cur = virusPos.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                        if (copyMap[nx][ny] == 0) {
                            copyMap[nx][ny] = 2;
                            virusPos.offer(new Pos(nx, ny));
                        }
                    }
                }
            }
            getCountOfSafeArea(copyMap);
        }

        public static void getCountOfSafeArea(int[][] copyMap) {
            // 안전 영역 크기 세기
            int safeArea = 0;
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (copyMap[i][j] == 0) {
                        safeArea++;
                    }
                }
            }
            answer = Math.max(safeArea, answer);
        }
    }

    public static class Main_14889_S1_스타트와링크 {

        static int N;
        static int S[][];
        static int M;
        static boolean visited[];
        static int minValue = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());
            M = N / 2;

            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[N]; // 초기화 안 해도 됨

            dfs(0, 0);
            System.out.println(minValue);
        }

        private static void dfs(int n, int idx) {
            if (n == M) { // 절반만큼 선택했다면
                int aSum = 0;
                int bSum = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (visited[i] && visited[j]) {
                            aSum += S[i][j];
                        } else if (!visited[i] && !visited[j]) {
                            bSum += S[i][j];
                        }
                    }
                }
                minValue = Math.min(minValue, Math.abs(aSum - bSum));
                return;
            }

            for (int i = idx; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(n + 1, i + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static class Main_14890_G3_경사로 {

        static int N, M;
        static int[][] map, map2;
        static int ans;
        static boolean[] visited;

        public static void solve(int[][] map) {
            for (int i = 0; i < N; i++) {
                visited = new boolean[N];
                boolean flag = true;
                int same = 1; // up
                int cnt = 0; // down
                for (int j = 1; j < N; j++) {
                    if (map[i][j - 1] == map[i][j] && !visited[j]) {
                        same++;
                        continue;
                    }
                    if (map[i][j - 1] == map[i][j] + 1) { // down
                        // M개 만큼 탐색
                        for (int k = j; k < Math.min(j + M, N); k++) {
                            if (map[i][j] == map[i][k]) {
                                cnt++;
                            } else {
                                flag = false;
                                break;
                            }
                        }
                        if (cnt < M) {
                            flag = false;
                            break;
                        }
                        // 경사로 설치
                        for (int k = 0; k < M; k++) {
                            visited[j + k] = true;
                        }
                        cnt = 0;
                        same = 0; // 경사로 설치할 때 사용했으니 초기화
                    } else if (map[i][j - 1] == map[i][j] - 1) { // up
                        if (same < M) {
                            flag = false;
                            break;
                        }
                        same = 1;
                    } else if (Math.abs(map[i][j - 1] - map[i][j]) >= 2) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ans++;
                }
            }
        }

        public static void input() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            map2 = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int k = Integer.parseInt(st.nextToken());
                    map[i][j] = k;
                    map2[j][i] = k;
                }
            }
        }

        public static void main(String[] args) throws IOException {
            input();
            solve(map); // 행 단위
            solve(map2); // 열 단위
            System.out.println(ans);
        }

    }

    public static class Main_15686_G5_치킨배달 {

        static int N, M;
        static int[][] city;
        static List<int[]> chickens;
        static List<int[]> houses;
        static int[] numbers;
        static int min;


        public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            city = new int[N][N];
            chickens = new ArrayList<>();
            houses = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    city[i][j] = Integer.parseInt(st.nextToken());
                    if (city[i][j] == 2) {
                        chickens.add(new int[]{i, j});
                    } else if (city[i][j] == 1) {
                        houses.add(new int[]{i, j});
                    }
                }
            }
            min = Integer.MAX_VALUE;
            numbers = new int[M];
            combination(0, 0);

            System.out.println(min);
        }

        public static void combination(int depth, int start) {
            if (depth == M) {
                // 최소 거리 구하기
                int answer = 0;
                for (int i = 0; i < houses.size(); i++) {
                    int dis = Integer.MAX_VALUE;
                    int[] house = houses.get(i);
                    for (int j = 0; j < M; j++) {
                        // 선택한 치킨 집으로 치킨 거리 구하기
                        int[] chicken = chickens.get(numbers[j]);
                        int nDis = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
                        dis = Math.min(dis, nDis);
                    }
                    answer += dis;
                }
                min = Math.min(min, answer);
                return;
            }
            for (int i = start; i < chickens.size(); i++) {
                numbers[depth] = i;
                combination(depth + 1, i + 1);
            }
        }
    }

    public static class Main_17140_G4_이차원배열과연산 {

        static int R, C, K;
        /*
            어떤 자료구조?
            크기가 정해져 있고 연산 후 나머지 자리에 0을 붙여야 하므로 100 * 100 만큼의 배열 할당
         */
        static int[][] map;
        static int RLEN, CLEN;
        static PriorityQueue<Info> pq;
        static class Info implements Comparable<Info> {
            int num;
            int cnt;
            Info(int num, int cnt) {
                this.num = num;
                this.cnt = cnt;
            }

            @Override
            public int compareTo(Info o) {
                // cnt -> num : 커지는 순으로
                if (this.cnt == o.cnt) {
                    return this.num - o.num;
                }
                return this.cnt - o.cnt;
            }
        }

        public static void print() {
            for (int i = 0; i < RLEN; i++) {
                for (int j = 0; j < CLEN; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }

        public static void solve() {
            int time = 0;
            // 조건에 맞게 정렬하기 위해
            pq = new PriorityQueue<>();
            while(true) {
                if (time > 100) {
                    System.out.println(-1);
                    return;
                }
                if (map[R][C] == K) {
                    break;
                }
                if (RLEN >= CLEN) {
                    R();
                } else {
                    C();
                }
    //            print();
    //            System.out.println();
                time++;
            }
            System.out.println(time);
        }
        public static void R() {
            for (int i = 0; i < RLEN; i++) {
                // 행 별로 개수 구하기
                Map<Integer, Integer> cnt = new HashMap<>();
                for (int j = 0; j < CLEN; j++) {
                    if (map[i][j] == 0) continue;
                    cnt.put(map[i][j], cnt.getOrDefault(map[i][j], 0) + 1);
                }
                // pq에서 정렬
                for (Integer key : cnt.keySet()) {
                    pq.add(new Info(key, cnt.get(key)));
                }
                // 정렬 결과 반영
                int idx = 0;
                while(!pq.isEmpty()) {
                    Info p = pq.poll();
                    map[i][idx++] = p.num;
                    map[i][idx++] = p.cnt;
                }
                // 크기 갱신
                CLEN = Math.max(idx, CLEN);
                if (CLEN >= 100) continue;

                // 뒤의 값 갱신 : 0으로 바꿔야 하는 경우 존재
                while(idx <= 99) {
                    map[i][idx++] = 0;
                    map[i][idx++] = 0;
                }
            }
        }
        public static void C() {
            for (int i = 0; i < CLEN; i++) {
                // 열 별로 개수 구하기
                Map<Integer, Integer> cnt = new HashMap<>();
                for (int j = 0; j < RLEN; j++) {
                    if (map[j][i] == 0) continue;
                    cnt.put(map[j][i], cnt.getOrDefault(map[j][i], 0) + 1);
                }
                // pq에서 정렬
                for (Integer key : cnt.keySet()) {
                    pq.add(new Info(key, cnt.get(key)));
                }
                // 정렬 결과 반영
                int idx = 0;
                while(!pq.isEmpty()) {
                    Info p = pq.poll();
                    map[idx++][i] = p.num;
                    map[idx++][i] = p.cnt;
                }
                // 크기 갱신
                RLEN = Math.max(idx, RLEN);
                // 100 넘어가면 버린다
                if (RLEN >= 100) continue;

                // 뒤의 값 갱신 : 0으로 바꿔야 하는 경우 존재
                while(idx <= 99) {
                    map[idx++][i] = 0;
                    map[idx++][i] = 0;
                }
            }
        }
        public static void input() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken()) - 1;
            C = Integer.parseInt(st.nextToken()) - 1;
            K = Integer.parseInt(st.nextToken());
            map = new int[100][100];
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            RLEN = 3;
            CLEN = 3;
        }
        public static void main(String[] args) throws IOException {
            input();
            solve();
        }
    }
}
