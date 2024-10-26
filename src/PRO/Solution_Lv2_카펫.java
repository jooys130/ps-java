package PRO;

class Solution_Lv2_카펫 {
    static int x, y;
    public int[] solution(int brown, int yellow) {
        // 가로 >= 세로
        int XY = yellow + brown;
        int X_Y = (brown + 4) / 2;
        for (int i = 1; i < XY; i++) {
            y = i;
            if (XY % y == 0) {
                x = XY / y;
            }
            if (x + y == X_Y) break;
        }
        return new int[] {x, y};
    }
}
