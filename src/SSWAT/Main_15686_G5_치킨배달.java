package SSWAT;

import java.io.*;
import java.util.*;

public class Main_15686_G5_치킨배달 {

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
