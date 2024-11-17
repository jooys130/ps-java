package PRO;

class Solution_PCCP2_실습용로봇 {
    // 상 우 하 좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int x, y, dir;
    public int[] solution(String command) {
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            if (c == 'G') {
                x = x + dx[dir];
                y = y + dy[dir];
            } else if (c == 'R') {
                dir = (dir + 1) % 4;
            } else if (c == 'L') {
                dir = (dir - 1 + 4) % 4;
            } else if (c == 'B') {
                x = x + dx[dir] * -1;
                y = y + dy[dir] * -1;
            }
        }
        return new int[] {x, y};
    }
}