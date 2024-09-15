package BOJ;

import java.io.*;
import java.util.*;

public class Main_20159_G4_동작그만밑장빼기냐 {
    static int cardCnt;
    static int[] cards;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cardCnt = Integer.parseInt(br.readLine());
        cards = new int[cardCnt];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cardCnt; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        // 밑장 빼기 안 했을 때
        for (int i = 0; i < cardCnt; i = i + 2) {
            ans += cards[i];
        }
        int tmp1 = ans; int tmp2 = ans;
        // 정훈이 타이밍
        for (int i = cardCnt - 1; i >= 0; i -= 2) {
            tmp1 -= cards[i - 1];
            tmp1 += cards[i];
            ans = Math.max(tmp1, ans);
        }
        // 다른 사람 타이밍
        for (int i = cardCnt - 2; i >= 1 ; i -= 2) {
            tmp2 += cards[i - 1];
            tmp2 -= cards[i];
            ans = Math.max(tmp2, ans);
        }
        System.out.println(ans);
    }
}
