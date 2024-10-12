package PRO;

class Solution_Lv2_스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int skillLen = skill.length();
        for (String tree: skill_trees) {
            String tmp = tree.replaceAll("[^" + skill + "]", "");
            boolean flag = false;
            // skill을 앞에서부터 끊어서 비교 && tmp가 ""인 경우 고려해서 0부터 시작
            for (int i = 0; i < skillLen + 1; i++) {
                if (tmp.equals(skill.substring(0, i))) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}