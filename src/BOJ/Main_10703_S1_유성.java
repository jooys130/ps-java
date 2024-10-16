package BOJ;

import java.io.*;
import java.util.*;

public class Main_10703_S1_유성 {
    static int R, S;
    static char[][] map;
    static Queue<int[]> pos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new char[R][S];
        pos = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < S; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        // 최대 내려가는 간격 구하기
        /*
            유성이 없는 경우 예외 처리
         */
        int minInterval = Integer.MAX_VALUE;
        for (int i = 0; i < S; i++) {
            int starEnd = -1;
            for (int j = 0; j < R; j++) {
                if (map[j][i] == 'X') {
                    starEnd = j;
                    pos.offer(new int[]{j, i});
                    map[j][i] = '.';
                }
                if (map[j][i] == '#' && starEnd != -1) {
                    minInterval = Math.min(minInterval, j - starEnd - 1);
                    break;
                }
            }
            // System.out.println(i + " " + starEnd + " " + minInterval);
        }
        // 내리기
        while(!pos.isEmpty()) {
            int[] cur = pos.poll();
            map[cur[0]+minInterval][cur[1]] = 'X';
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}