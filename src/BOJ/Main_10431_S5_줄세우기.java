package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10431_S5_줄세우기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tc = Integer.parseInt(st.nextToken());
            int cnt = 0;
            int[] arr = new int[20];
            int[] newArr = new int[20];
            for (int j = 0; j < 20; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            newArr[0] = arr[0];
            for (int j = 1; j < 20; j++) {
                newArr[j] = arr[j];
                int idx = -1;
                for (int k = 0; k < j; k++) {
                    if (newArr[k] > arr[j]) {
                        idx = k;
                        break;
                    }
                }
                if (idx != -1) {
                    int tmp = newArr[j];
                    for (int t = j; t > idx; t--) {
                        newArr[t] = newArr[t - 1];
                        cnt++;
                    }
                    newArr[idx] = tmp;
                }
            }
            sb.append(tc + " " + cnt).append("\n");
        }
        System.out.println(sb);
    }
}
