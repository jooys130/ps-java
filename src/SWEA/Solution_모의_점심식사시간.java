package SWEA;

import java.util.*;
import java.io.*;

public class Solution_모의_점심식사시간 {
    static class People implements Comparable<People>{
        int x; int y; int time;

        People(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
        @Override
        public int compareTo(People o) {
            return this.time - o.time;
        }
        @Override
        public String toString() {
            return x + " " + y + " " + time;
        }
    }
    static List<int[]> stairs; // 계단 두 개 위치
    static List<int[]> p; // 사람 위치
    static Queue<People> stair1, stair2; // 부분집합 생성
    static boolean[] visited; // subset의 isSelected 역할
    static int N, pCnt, min;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            stairs = new ArrayList<>();
            p = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 2) {
                        stairs.add(new int[] {i, j, map[i][j]});
                    } else if (map[i][j] == 1) {
                        p.add(new int[] {i, j});
                    }
                }
            };

            pCnt = p.size();
            visited = new boolean[pCnt];
            min = Integer.MAX_VALUE;
            subset(0);
            System.out.println("#" + tc + " " + min);
        }
    }
    public static void subset(int idx) {
        if (idx == pCnt) {
            stair1 = new PriorityQueue<>();
            stair2 = new PriorityQueue<>();
            setStairs(visited);
            min = Math.min(min, go());
            return;
        }

        visited[idx] = true;
        subset(idx+1);

        visited[idx] = false;
        subset(idx+1);
    }
    public static int go() {
        /*

         */
        int len1 = stairs.get(0)[2];
        int size1 = stair1.size();
        int len2 = stairs.get(1)[2];
        int size2 = stair2.size();

        int[] time1 = new int[size1];
        for (int i = 0; i < size1; i++) {
            time1[i] = stair1.poll().time;
            if (i < 3) time1[i] += len1;
        }
        for (int i = 3; i < size1; i++) {
            if (time1[i-3] > time1[i]) time1[i] = time1[i-3];
            time1[i] += len1;
        }

        int[] time2 = new int[size2];
        for (int i = 0; i < size2; i++) {
            time2[i] = stair2.poll().time;
            if (i < 3) time2[i] += len2;
        }
        for (int i = 3; i < size2; i++) {
            if (time2[i-3] > time2[i]) time2[i] = time2[i-3];
            time2[i] += len2;
        }

        if (size1 == 0) return time2[size2-1];
        if (size2 == 0) return time1[size1-1];

        return Math.max(time1[size1-1], time2[size2-1]);
    }
    public static void setStairs(boolean[] visited) {
        int[] stairA = stairs.get(0);
        int[] stairB = stairs.get(1);
        for (int i = 0; i < pCnt; i++) {
            int[] people = p.get(i);
            if (visited[i]) {
                stair1.add(new People(people[0], people[1], calc(stairA, people)));
            } else {
                stair2.add(new People(people[0], people[1], calc(stairB, people)));
            }
        }
    }
    public static int calc(int[] stair, int[] people) {
        return Math.abs(stair[0] - people[0]) + Math.abs(stair[1] - people[1]) + 1; // 도착하고 나서 1분 뒤 내려가기 시작
//				+ stair[2]; // 길이까지 더해야 해당 사람이 나온데까지 걸리는 시간
    }
}
