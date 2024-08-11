package BOJ;

import java.io.*;
import java.util.*;

public class Main_2110_G4_공유기설치 {
    static int N, C; // 집의 개수 N, 공유기 개수 C
    static int[] houses, routers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);
        // 인덱스가 아닌 거리 !!
        int low = 1;
        int high = houses[N-1] - houses[0];
        int answer = 0; // 가장 인접한 두 공유기 사이가 최대가 되는 경우
        while(low <= high) {
            int mid = (low + high) / 2;
            //  mid로 공유기 설치할 수 있는 개수
            int cnt = 1;
            int tmp = houses[0];
            for (int i = 1; i < N; i++) {
                if (houses[i] - tmp >= mid)  {
                    // 커도 설치할 수 있음
                    cnt++;
                    tmp = houses[i];
                }
            }
            // 이분탐색
            if (cnt >= C) {
                // 거리 늘리기
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(answer);
    }
}