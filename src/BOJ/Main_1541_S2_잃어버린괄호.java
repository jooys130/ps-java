package BOJ;

import java.io.*;

public class Main_1541_S2_잃어버린괄호 {
    static int ans;
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        arr = input.split("-");
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) ans += calc(i);
            else ans -= calc(i);
        }
        System.out.println(ans);
    }
    public static int calc(int x) {
        String[] t = arr[x].split("\\+");
        int tmp = 0;
        for (int i = 0; i < t.length; i++) {
            tmp += Integer.parseInt(t[i]);
        }
        return tmp;
    }
}