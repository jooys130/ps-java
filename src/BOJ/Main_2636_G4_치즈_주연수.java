package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2636_G4_치즈_주연수 {
	
    /*
     * 시간: 164 ms
     * 메모리: 15572 kb
     */
	
	static int N, M, cnt, T;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
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
				if (map[i][j] == 1) {
					cnt++;
				}
				
			}
		}
		int tmp = 0;
		while (cnt > 0) {
			visited = new boolean[N][M];
			tmp = cnt; T++;
			bfs(0, 0);
		}
		System.out.println(T + "\n" + tmp);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
					continue;
				}
				visited[nx][ny] = true;
				if (map[nx][ny] == 1) {
					cnt--;
					map[nx][ny] = 0;
				} else if (map[nx][ny] == 0) {
					q.add(new int[] {nx, ny});
				}
			}
		}
	}

}
