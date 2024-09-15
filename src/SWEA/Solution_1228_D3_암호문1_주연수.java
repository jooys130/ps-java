package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1228_D3_암호문1_주연수 {

    /*
     * 수행 시간:   100ms
     * 메모리:	18,468KB
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i < 11; i++) {
            int N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            List<Integer> arr = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }
//            System.out.println(arr);

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                if(st.nextToken().equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int l = 0; l < y; l++) {
                        arr.add(x++, Integer.parseInt(st.nextToken()));
                    }
                    /*
                        (다른 방법)
                        1. 새로운 연결 리스트 생성하여 arr에 addAll()
                        2. x++ 대신 (x + l)
                     */
                }
            }
            sb.append("#" + i);
            for (int j = 0; j < 10; j++) {
                sb.append(" " + arr.get(j));
            }
            System.out.println(sb);
        }
    }

}
