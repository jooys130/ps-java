package PRO;

class Solution_Lv1_동영상재생기 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int cur = toSec(pos);
        int end = toSec(video_len);
        int s_start = toSec(op_start);
        int s_end = toSec(op_end);
        for (String command : commands) {
            if (s_start <= cur && cur < s_end) {
                cur = s_end;
            }
            if (command.equals("prev")) {
                cur -= 10;
                if (cur < 0) cur = 0;
                // System.out.println("<== " + toString(cur));
            } else if (command.equals("next")) {
                cur += 10;
                if (cur > end) cur = end;
                // System.out.println("==> " + toString(cur));
            }
            if (s_start <= cur && cur < s_end) {
                cur = s_end;
            }
        }
        return toString(cur);
    }
    public int toSec(String times) {
        int min = Integer.parseInt(times.split(":")[0]);
        int sec = Integer.parseInt(times.split(":")[1]);
        return min * 60 + sec;
    }
    public String toString(int times) {
        int min = times/60;
        int sec = times%60;
        String mm = String.valueOf(min);
        String ss = String.valueOf(sec);
        if (min < 10) mm = "0" + mm;
        if (sec < 10) ss = "0" + ss;
        return mm + ":" + ss;
    }
}