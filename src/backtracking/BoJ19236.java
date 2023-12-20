package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish {

    int num;
    int x, y;
    int dir;
    boolean alive;

    public Fish(int num, int x, int y, int dir, boolean alive) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.alive = alive;
    }
}

public class BoJ19236 {

    static Fish[] fishes; // 물고기 숫자 방향 저장
    static int[][] space; // 2차원 그래프에 물고기 숫자 저장
    // 반시계 방향: 북, 북서, 서, 남서, 남, 남동, 동, 북동
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int EMPTY = 0, SHARK = -1;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fishes = new Fish[17];
        space = new int[4][4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                fishes[num] = new Fish(num, i, j, dir-1, true);
                space[i][j] = num;
            }
        }
        // 상어 투입
        int fishNum = space[0][0];
        int sharkDir = fishes[fishNum].dir;

        space[0][0] = SHARK;
        fishes[fishNum].alive = false;
        fishes[fishNum].x = -1;
        fishes[fishNum].y = -1;
        fishes[fishNum].dir = -1;
        answer += fishNum;

        dfs(0, 0, sharkDir, answer);

        System.out.println(answer);

    }

    private static void dfs(int sharkX, int sharkY, int sharkDir, int eatSum) {
        // 백업 위해
        int[][] tmpSpace = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                tmpSpace[i][j] = space[i][j];
            }
        }
        Fish[] tmpFishes = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            Fish fish = fishes[i];
            tmpFishes[i] = new Fish(fish.num, fish.x, fish.y, fish.dir, fish.alive);
        }

        // 물고기 이동
        moveAllFishes();
//        for (int i = 0; i < 4; i++) {
//            System.out.println(Arrays.toString(space[i]));
//        }
//        System.out.println();

        // moveShark() 백트래킹 ** 정답 갱신 어떻게??
        for (int i = 1; i <= 3; i++){
            int nx = sharkX + (dx[sharkDir] * i);
            int ny = sharkY + (dy[sharkDir] * i);

            if (0 > nx || nx >= 4 || 0 > ny || ny >= 4){
                continue;
            }
            // 물고기가 있는 경우 이동 가능
            if (1 <= space[nx][ny] && space[nx][ny] <= 16) {
                int fishNum = space[nx][ny];
                int nextDir = fishes[fishNum].dir;

                // 상어가 물고기 먹음
                space[sharkX][sharkY] = EMPTY;
                space[nx][ny] = SHARK;
                fishes[fishNum].alive = false;

                // 정답 갱신
                answer = Math.max(answer, eatSum + fishNum);

                dfs(nx, ny, nextDir, eatSum + fishNum);

                // backTrack
                space[nx][ny] = fishNum;
                space[sharkX][sharkY] = SHARK;
                fishes[fishNum].alive = true;

            }

        }

        space = tmpSpace;
        fishes = tmpFishes;

    }

    private static void moveAllFishes() {
        // 작은 물고기부터 살아있다면 이동
        for (int i = 1; i <= 16; i++) {
            if (fishes[i].alive) {
                moveFish(i);
            }
//            for (int k = 0; k < 4; k++) {
//                System.out.println(Arrays.toString(space[k]));
//            }
//            System.out.println();
        }
    }

    private static void moveFish(int idx) {
        int x = fishes[idx].x;
        int y = fishes[idx].y;
        int dir = fishes[idx].dir;

        // 반시계 방향 회전
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[(dir + d) % 8];
            int ny = y + dy[(dir + d) % 8];
            // 공간의 경계를 넘거나 상어가 있는 경우
            if (0 > nx || nx >= 4 || 0 > ny || ny >= 4 || space[nx][ny] == SHARK) {
                continue;
            }
            // 위치 저장
            fishes[idx].dir = (fishes[idx].dir + d) % 8;
            if (space[nx][ny] == EMPTY){
                space[x][y] = EMPTY;
                space[nx][ny] = idx;
                fishes[idx].x = nx;
                fishes[idx].y = ny;
                break;
            } else if (1 <= space[nx][ny] && space[nx][ny] <= 16) {
                // swap
                int tmp = space[nx][ny];
                space[nx][ny] = space[x][y];
                space[x][y] = tmp;
                fishes[idx].x = nx;
                fishes[idx].y = ny;
                fishes[tmp].x = x;
                fishes[tmp].y = y;
                break;
            }
        }
    }
}