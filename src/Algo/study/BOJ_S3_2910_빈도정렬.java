package Algo.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S3_2910_빈도정렬 {

    /*
        (문제 설명)
        등장하는 숫자의 빈도순대로 정렬
        등장하는 횟수가 같다면 먼저 나온 것이 앞에 있어야 한다

        (풀이)
        HashMap에 빈도수를 저장한다
        횟수가 같을 때만 순서대로 출력해야 하는데
        HashMap에 순서도 저장하자

        (추가)
        LinkedHashMap을 사용하면 순서를 저장할 수 있다
        생성한 HashMap에 대해 keySet 기준으로 빈도수만 비교해서 정렬한다
        링크 :

        (객체로 정렬한다면)
        링크 :
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            // 크기를 미리 아니까 지정하기
            ArrayList<Integer> value = new ArrayList<>(2);
            value.add(i); value.add(1); // order count
            if (map.containsKey(arr[i])) {
                value.set(0, map.get(arr[i]).get(0));
                value.set(1, map.get(arr[i]).get(1) + 1);
            }
            map.put(arr[i], value);
        }

        // 값으로 내림차순 정렬
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (map.get(o1).get(1) == map.get(o2).get(1)) {
                    // 순서 오름차순
                    return map.get(o1).get(0) - map.get(o2).get(0);
                } else {
                    // 개수 내림차순
                    return map.get(o2).get(1) - map.get(o1).get(1);
                }
            }
        });

        // 키 값이 같다면 받은 순서대로 출력
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> iter = keySet.iterator();
        while(iter.hasNext()) {
            Integer key = iter.next();
            Integer value = map.get(key).get(1);
            for (int i = 0; i < value; i++) {
                sb.append(key + " ");
            }
        }

        System.out.println(sb);

    }
}
