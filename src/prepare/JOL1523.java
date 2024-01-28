package prepare;

import java.util.Scanner;

public class JOL1523 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int type = sc.nextInt();

        if (n < 0 || n > 100 || type < 1 || type > 3) {
            System.out.println("INPUT ERROR!");
            return;
        }
        switch(type) {
            case 1:
                for (int i = 1; i < n + 1; i++) {
                    for (int j = 0; j < i; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = n + 1; i > 1; i--) {
                    for (int j = 1; j < i; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                // switch 문 사용시 break 주의
                break;
            case 3:
                int k = 2 * n - 1;
                for (int i = 1; i < 2 * n; i = i + 2) {
                    for (int j = 0; j < (k - i) / 2; j++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < i; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
        }
    }
}