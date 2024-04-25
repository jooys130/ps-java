package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1931_S1_회의실배정 {
    static int N;
    static List<Meeting> meetings;
    static class Meeting implements Comparable<Meeting>{
        int start, end;
        Meeting (int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Meeting o) {
            if (o.end == this.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(start, end));
        }
        Collections.sort(meetings);

        int cnt = 0;
        int end = 0;
        for (int i = 0; i < N; i++) {
            if (end <= meetings.get(i).start) {
                cnt++;
                end = meetings.get(i).end;
            }
        }
        System.out.println(cnt);
    }
}
