package PRO;

class Solution_Lv2_문자열압축 {
    static int ans = Integer.MAX_VALUE;
    static int LEN;
    static StringBuilder sb;
    public int solution(String s) {
        LEN = s.length();
        if (LEN == 1) return 1;
        for (int i = 1; i <= LEN/2; i++) {
            sb = new StringBuilder();
            int cnt = 1;
            String base = s.substring(0, i); // i가 size를 의미
            for (int next = i; next <= LEN; next+=i) { // i 단위만큼 비교
                int endIdx = Math.min(next + i, LEN); // 범위 넘어가는 경우 안 됨
                String compare = s.substring(next, endIdx);
                if (base.equals(compare)) {
                    cnt++;
                } else {
                    if (cnt > 1) sb.append(cnt);
                    sb.append(base);
                    cnt = 1;
                    base = compare; // 마지막인 경우 넣어주기 위해서 갱신 #25
                }
            }
            sb.append(base);
            // System.out.println("size:" + i + " => " + sb);
            if (sb.length() < ans) ans = sb.length();
        }
        return ans;
    }
}