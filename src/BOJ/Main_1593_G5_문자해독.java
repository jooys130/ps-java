package BOJ;

import java.io.*;
import java.util.*;

public class Main_1593_G5_문자해독 {
    static int targetLen;
    static int totalLen;
    static String target;
    static String total;
    static int[] targetArr;
    static int[] tmpArr;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        targetLen = Integer.parseInt(st.nextToken());
        totalLen = Integer.parseInt(st.nextToken());
        target = br.readLine();
        total = br.readLine();
        targetArr = new int[26 * 2];
        tmpArr = new int[26 * 2];
        for (int i = 0; i < targetLen; i++) {
            char t = target.charAt(i);
            putArr(t, targetArr);
        }
        for (int i = 0; i < totalLen; i++) {
            if (i - targetLen >= 0) {
                popArr(total.charAt(i-targetLen), tmpArr);
            }
            putArr(total.charAt(i), tmpArr);
            if (Arrays.equals(targetArr, tmpArr)) {
                ans++;
            }
        }
        System.out.println(ans);
    }
    // 65: 'A', 97 : 'a'
    public static void putArr(char ch, int[] arr) {
        if (ch >= 'a') {
            arr[ch - 'a']++;
        } else {
            arr[ch - 'A' + 26]++;
        }
    }
    public static void popArr(char ch, int[] arr) {
        if (ch >= 'a') {
            arr[ch - 'a']--;
        } else {
            arr[ch - 'A' + 26]--;
        }
    }
}
