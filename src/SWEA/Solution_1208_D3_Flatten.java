package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1208_D3_Flatten {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10 ; tc++) {
            sb.append("#").append(tc).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[] H = new int[100];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                Arrays.sort(H);
                if (H[0] == H[99]) break;
                H[0]++; H[99]--;
            }

            Arrays.sort(H);
            sb.append(H[99] - H[0]).append("\n");
        }
        System.out.println(sb);
    }
}
