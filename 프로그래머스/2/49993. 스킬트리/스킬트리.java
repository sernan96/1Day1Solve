import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String x: skill_trees){
            boolean possible = true;
            int previous = -1;
            StringBuilder sb = new StringBuilder();
            for(char y: x.toCharArray()){//하나씩 순회하면서 charAt을 통해 전의 수보다 음수가 아닌 작은 수가 나오면 불가능
                int temp = skill.indexOf(y);
                if(temp>=0 && previous<temp){//skill 조건에 있으면서 이전보다 큰경우
                    previous = temp;
                    sb.append(y);
                }
                else if(temp>=0 && previous>temp){//skill tree 순서가 맞지 않는 경우
                    possible = false;
                    break;
                }
            }
            if(sb.length()!=skill.length()){
                int index=0;
                for(char k: sb.toString().toCharArray()){
                    if(index++!=skill.indexOf(k)){
                        possible = false;
                        break;
                    }
                }
            }
            if(possible)
            {
                answer++;
            }
        }
        return answer;
    }
}