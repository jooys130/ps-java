package BOJ;

import java.io.*;
import java.util.*;

public class Main_10816_S4_숫자카드2 {
    /*
        자료구조 없이 인덱스로 이분탐색하기
        "중복 원소의 개수"는 왼쪽 인덱스와 오른쪽 인덱스를 구해서 뺀다
        : lower bound, upper bound
        (각각 이분 탐색을 하지 않으니 반례가 존재하더라)
     */
    static int N; // 상근이가 가지고 있는 숫자 카드의 개수
    static int[] cards; // 숫자 카드에 적혀있는 정수
    static int M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        // 정렬
        Arrays.sort(cards);
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int k = Integer.parseInt(st.nextToken());
            sb.append(upperBound(k) - lowerBound(k)).append(' ');
        }
        System.out.println(sb);
    }
    static int lowerBound(int k) {
        // "k 값 이상"을 가지고 있는 가장 작은 인덱스(처음 만난)
        int low = 0;
        int high = N;
        while (low < high) {
            int mid = low + (high - low) / 2;
            /*
                == (low + high) / 2
                high가 Integer.MAX_VALUE인 경우 고려
             */
            if (cards[mid] >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    static int upperBound(int k) {
        // "k 값 초과"를 가지고 있는 가장 작은 인덱스(처음 만난)
        int low = 0;
        int high = N;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (cards[mid] > k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}