package BOJ;

import java.io.*;
import java.util.*;

public class Main_21608_G5_상어초등학교 {
    static int N;
    static Map<Integer, List<Integer>> likes;
    static int[] order; // 순서 기억
    static int[][] map;
    static class Pos implements Comparable<Pos>{
        int x; int y; int adj; int blank;

        public Pos(int x, int y, int adj, int blank) {
            super();
            this.x = x;
            this.y = y;
            this.adj = adj;
            this.blank = blank;
        }
        @Override
        public int compareTo(Pos o) {
            if (this.adj == o.adj) {
                if (this.blank == o.blank) {
                    if (this.x == o.x) {
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return o.blank - this.blank;
            }
            return o.adj - this.adj;
        }
        @Override
        public String toString() {
            return x + " " + y + " " + adj + " " + blank;
        }
    }
    static PriorityQueue<Pos> pq;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};
    static int score;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        likes = new HashMap<>(N*N);
        map = new int[N][N];
        order = new int[N*N];
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            order[i] = key;
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                l.add(Integer.parseInt(st.nextToken()));
            }
            likes.put(key, l);
        }
        solve();
        System.out.println(score);
    }
    public static void solve() {
        for (int o = 0; o < N * N; o++) {
            int key = order[o];
            pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N ; j++) {
                    if (map[i][j] == 0) {
                        int adj = 0;
                        int blank = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                            if (likes.get(key).contains(map[nx][ny])) {
                                adj++; // 인접한 칸에 좋아하는 학생 수
                            } else if (map[nx][ny] == 0) {
                                blank++;
                            }
                        }
                        pq.add(new Pos(i, j, adj, blank));
                    }
                }
            }

            // 학생 넣기
            Pos p = pq.poll();
            map[p.x][p.y] = key;
        }
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
        calc();
    }
    public static void calc() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                int key = map[i][j];
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if(likes.get(key).contains(map[nx][ny])) {
                        cnt++;
                    }
                }
                score += cnt == 0 ? 0 : Math.pow(10, cnt-1);
            }
        }
    }
}
