package PRO;

import java.util.*;
class Solution_Lv2_뒤에있는큰수찾기 {
    // 이중포문 -> 시간초과 O(N^2)
    // -> 스택 사용 "큰 것만 남아있게 한다" + 뒤에서부터 탐색
    // 앞에서부터 탐색 돌려면 stack에 index를 저장해서 정보를 기억해둔다
    static int N;
    static Stack<Integer> stack = new Stack<>();
    static int[] answer;
    public int[] solution(int[] numbers) {
        N = numbers.length;
        answer = new int[N];
        for (int i = N-1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(numbers[i]);
                answer[i] = -1;
            }
            else {
                while (!stack.isEmpty()) {
                    int top = stack.peek();
                    if (top > numbers[i]) {
                        answer[i] = top;
                        stack.push(numbers[i]);
                        break;
                    } else {
                        answer[i] = -1;
                        stack.pop();
                    }
                }
                stack.push(numbers[i]);
            }
        }
        return answer;
    }
}