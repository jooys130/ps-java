package BOJ;

import java.io.*;
import java.util.*;

public class Main_1094_S5_막대기 {
    static int N;
    static List<Integer> tmp;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // stream
        tmp = new ArrayList<>();
        tmp.add(64);
        while(true) {
            Collections.sort(tmp);
            int sum = tmp.stream().mapToInt(Integer::intValue).sum();
            if (sum == N) break;
            if (sum > N) {
                int shortest = tmp.get(0)/2;
                tmp.remove(0);
                tmp.add(shortest);
                int t = tmp.stream().mapToInt(Integer::intValue).sum();
                if (t < N) tmp.add(shortest);
            }
        }
        System.out.println(tmp.size());
        // bitmask
        for(int i=0; i<7; i++) {
            if ((N&(1 << i)) != 0) cnt++;
        }
        System.out.println(cnt);
    }
}
