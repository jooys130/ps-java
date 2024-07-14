package PRO;

class Solution_Lv2_호텔대실 {
    static final int HOUR = 60;
    static final int CLEAN = 10; // 청소 시간 동안 다음 손님이 사용할 수 없다
    static final int SIZE = 24 * 60 + 10;
    static int[] rooms;
    public int solution(String[][] book_time) {
        rooms = new int[SIZE];
        for (int i = 0; i < book_time.length; i++) {
            String in = book_time[i][0];
            String out = book_time[i][1];
            // 시작 시간과 끝 시간 표시
            rooms[getTime(in)] += 1;
            rooms[getTime(out) + CLEAN] += -1;
        }
        // 누적합을 통해 해당 시간에 사용되는 방 개수 구하기
        int answer = 0;
        for (int i = 1; i < SIZE; i++) {
            rooms[i] += rooms[i-1];
            answer = Math.max(answer, rooms[i]);
        }
        return answer;
    }
    public int getTime(String clock) {
        String[] time = clock.split(":");
        return Integer.parseInt(time[0]) * HOUR + Integer.parseInt(time[1]);
    }
}