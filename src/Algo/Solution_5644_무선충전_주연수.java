package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class AP {
    int x;
    int y;
    int c;
    int p;

    public AP(int x, int y, int c, int p) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }
}


public class Solution_5644_무선충전_주연수 {

    static int M, A;
    static int[] moveA, moveB;
    static AP[] aps;
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moveA = new int[M+1];
            moveB = new int[M+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < M+1; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < M+1; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            aps = new AP[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                aps[i] = new AP(x, y, c, p);
            }


            int xA = 1; int xB = 10;
            int yA = 1; int yB = 10;
            int ans = 0;
            for (int i = 0; i < M+1; i++) {
                xA += dx[moveA[i]];
                yA += dy[moveA[i]];
                xB += dx[moveB[i]];
                yB += dy[moveB[i]];
                ans += check(xA, yA, xB, yB);
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
    private static int check(int xA, int yA, int xB, int yB) {
        int max = 0;
        for (int a = 0; a < A; a++) {
            for (int b = 0; b < A; b++) {
                int sum = 0;
                int pA = charge(a, xA, yA);
                int pB = charge(b, xB, yB);
                if (a != b) {
                    sum = pA + pB;
                } else {
                    sum = Math.max(pA, pB);
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }
    private static int charge(int k, int x, int y) {
        int dis = Math.abs(aps[k].x - x) + Math.abs(aps[k].y - y);
        if (dis <= aps[k].c) return aps[k].p;
        return 0;
    }

}
