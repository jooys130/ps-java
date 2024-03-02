package BOJ;


import java.io.*;
import java.util.*;


public class Main_2457_G3_공주님의정원 {

    static class Flower implements Comparable<Flower> {

        int start;
        int end;

        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            // 시작일 빠른(작은), 종료일 늦은(큰) 순으로
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Flower[] flowers = new Flower[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());
            // 날짜를 int로 저장
            int start = startMonth * 100 + startDay;
            int end = endMonth * 100 + endDay;
            flowers[i] = new Flower(start, end);
        }

        Arrays.sort(flowers);

        int cnt = 0;
        int max = 0;
        int idx = 0;
        int START = 301;
        int END = 1201;
        while (START < END) {
            boolean check = false;

            // 이거 돌았는데 선택 못 한 거면 뒤에 더 안 봐도 됨
            for (int i = idx; i < N; i++) {
                if (flowers[i].start > START) {
                    // 더 늦게 시작하는 경우
                    break;
                }
                if (max < flowers[i].end) {
                    // 꽃 선택
                    check = true;
                    max = flowers[i].end;
                    idx = i + 1;
                }
            }

            if (check) {
                START = max;
                cnt++;
            } else {
                // 더 이상 탐색하지 않아도 됨
                break;
            }
        }

        System.out.println((max < END) ? 0 : cnt);
    }
}
