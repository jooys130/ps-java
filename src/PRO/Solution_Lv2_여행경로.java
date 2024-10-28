package PRO;

import java.util.*;
class Solution_Lv2_여행경로 {
    static List<String> routes;
    static boolean[] visited;
    public String[] solution(String[][] tickets) {
        routes = new ArrayList<>();
        visited = new boolean[tickets.length];
        dfs(0, "ICN", "ICN", tickets);
        // System.out.println(routes);
        Collections.sort(routes);
        String[] answer = routes.get(0).split(" ");
        return answer;
    }
    public void dfs(int cnt, String route, String start, String[][] tickets) {
        if (cnt == tickets.length) {
            routes.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                String end = tickets[i][1];
                dfs(cnt + 1, route + " " + end, end, tickets);
                visited[i] = false;
            }
        }
    }
}