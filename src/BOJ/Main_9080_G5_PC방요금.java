package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9080_G5_PC방요금 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String[] time = st.nextToken().split(":");
            int gaming = Integer.parseInt(st.nextToken());
            System.out.println(solve(time, gaming));
        }
    }
    private static int solve(String[] time, int gaming) {
        int hour = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        int fee = 0;
        // System.out.println(hour + ":" + min + " " + gaming + " " + fee);
        while (gaming > 0) {
            if ((hour >= 22 || hour < 3) && gaming >= 300) {
                while(hour != 8) {
                    hour = (hour + 1) % 24;
                    gaming -= 60;
                }
                fee += 5000; // 야간정기권이 더 유리
                gaming += min;
                min = 0;
            } else {
                fee += 1000;
                hour = (hour + 1) % 24;
                gaming -= 60;
            }
            // System.out.println(hour + ":" + min + " " + gaming + " " + fee);
        }
        return fee;
    }
}
