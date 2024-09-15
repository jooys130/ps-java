package tmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1303 {
    static int n, m, nowX, nowY;
    static char war[][];
    static boolean visited[][];
    static int cnt = 0;
    static int white_cnt = 0;
    static int blue_cnt = 0;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 지도 만들기
        war = new char[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++){
            String s = br.readLine();
            for(int j = 0; j < n; j++){
                war[i][j] = s.charAt(j);
            }
        }

        // DFS로 탐색
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j]) {
                    cnt = 0;
                    dfs(i, j, war[i][j]);
                    if (war[i][j] == 'W'){
                        white_cnt += cnt * cnt;
                    }
                    else{
                        blue_cnt += cnt * cnt;
                    }
                }
            }
        }
        System.out.println(white_cnt + " " + blue_cnt);
    }

    private static void dfs(int x, int y, char color){
        visited[x][y] = true;
        cnt += 1;

        for (int i = 0; i < 4; i++){
            nowX = x + dx[i];
            nowY = y + dy[i];
            if (nowX >= 0 && nowY >= 0 && nowX < m && nowY < n){
                if (!visited[nowX][nowY] && war[nowX][nowY] == color){
                    dfs(nowX, nowY, color);
                }
            }
        }
    }
}

