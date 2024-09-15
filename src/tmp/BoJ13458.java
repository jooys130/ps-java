package tmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoJ13458 {

    static int[] students;
    static long cnt = 0;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        students = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            students[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] proctor = new int[2];
        proctor[0] = Integer.parseInt(st.nextToken());
        proctor[1] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++){
            // 총감독관 추가
            cnt += 1;
            students[i] -= proctor[0];
            // 부감독관 추가
            if (students[i] > 0) {
                if (students[i] % proctor[1] != 0){
                    cnt += (students[i] / proctor[1]) + 1;
                } else {
                    cnt += students[i] / proctor[1];
                }
            }
            System.out.println(cnt);
        }

        System.out.println(cnt);

    }

}
