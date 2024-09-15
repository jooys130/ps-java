package BOJ;

import java.io.*;
import java.util.*;

public class Main_1244_S4_스위치켜고끄기 {

    /*
        수행 시간: 148ms
        메모리: 15804KB
        푼 방법: 스위치 상태 변경 시 XOR 연산자 사용
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());    // 스위치 개수
        int[] arr = new int[count + 1];                 // 스위치 상태
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < count + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int studentCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken()); // 1: 남학생, 2: 여학생
            int num = Integer.parseInt(st.nextToken());  // 스위치 번호
            if (type == 1) {
                for (int j = 1; j < count + 1; j++) {
                    if (j % num == 0) {
                        arr[j] ^= 1;
                    }
                }
            } else {
                arr[num] ^= 1;
                int start = num - 1;
                int end = num + 1;
                while (start >= 1 && end < count + 1) {
                    if (arr[start] != arr[end]) {
                        break;
                    }
                    arr[start--] ^= 1;
                    arr[end++] ^= 1;
                }
            }
        }
        for (int i = 1; i < count + 1; i++) {
            System.out.print(arr[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }
}