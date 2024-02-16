package Algo;

import java.io.*;
import java.util.*;

public class Main_17135_G3_캐슬디펜스_주연수 {

    static int N, M, D;
    static int[][] map;
    static int[] archer;
    static int ans, enemyCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        archer = new int[3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) enemyCount++;
            }
        }

        comb(0, 0);
        System.out.println(ans);
    }

    private static void comb(int cnt, int start) {
        if (cnt == 3) {
            int[][] copyMap = init(); // map -> copyMap
            ans = Math.max(ans, attack(copyMap));
            return;
        }

        for (int i = start; i < M; i++) {
            archer[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    private static int attack(int[][] copyMap) {
        int cnt = enemyCount;
        int remove = 0;
        while (cnt > 0) {
            List<int[]> enemy = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int x = N;
                int y = archer[i];
                int minDist = Integer.MAX_VALUE;

                int[] target = {-1, -1};
                for (int j = N - 1; j >= 0; j--) {
                    for (int k = 0; k < M; k++) {
                        if (copyMap[j][k] == 1) {
                            int dist = Math.abs(x - j) + Math.abs(y - k);
                            if(dist <= D) {
                                if (minDist > dist) {
                                    minDist = dist;
                                    target[0] = j;
                                    target[1] = k;
                                } else if (minDist == dist) {
                                    if (target[1] > k) {
                                        target[0] = j;
                                        target[1] = k;
                                    }
                                }
                            }
                        }
                    }
                }
                if(!(target[0] == -1 && target[1] == -1)) {
                    boolean flag = false;
                    for(int[] pos : enemy) {
                        if(pos[0] == target[0] && pos[1] == target[1]) {
                            flag = true; // 같다
                            break;
                        }
                    }
                    if(!flag) {
                        enemy.add(new int[]{target[0], target[1]});
                    }
                }
            }

            for (int[] pos : enemy) {
                copyMap[pos[0]][pos[1]] = 0;
                remove++;
                cnt--;
            }

            // down
            for (int i = N-1; i > 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (i == N-1 && copyMap[i][j] == 1) {
                        cnt--;
                    }
                    copyMap[i][j] = copyMap[i-1][j];
                }
            }
            for(int j=0; j<M; j++) {
                copyMap[0][j] = 0;
            }

        }
        return remove;
    }

    private static int[][] init() {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }
}
