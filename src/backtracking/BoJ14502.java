package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    };
}

public class BoJ14502 {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
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
