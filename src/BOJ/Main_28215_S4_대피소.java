package BOJ;

import java.io.*;
import java.util.*;

public class Main_28215_S4_대피소 {
    static int N, K;
    static class Pos {
        int x; int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return x + " " + y;
        }
    }
    static List<Pos> houses;
    static List<Pos> shelter;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // N개의 집 중 K개의 대피소 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        houses = new ArrayList<>();
        shelter = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            houses.add(new Pos(x, y));
        }
        combi(0, 0);
        System.out.println(ans);
    }
    public static void combi(int idx, int start) {
        if (idx == K) {
            ans = Math.min(calc(), ans);
            return;
        }
        for (int i = start; i < houses.size(); i++) {
            shelter.add(houses.get(i));
            combi(idx+1, i+1);
            shelter.remove(shelter.size()-1);
        }
    }
    public static int calc() {
        int maxValue = 0;
        for (Pos house : houses) {
            // 집에서 가장 가까운 대피소
            int minDis = Integer.MAX_VALUE;
            for (Pos s : shelter) {
                minDis = Math.min(getDis(s.x, house.x, s.y, house.y), minDis);
            }
            maxValue = Math.max(minDis, maxValue);
        }
        return maxValue;
    }
    public static int getDis(int x1, int x2, int y1, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}