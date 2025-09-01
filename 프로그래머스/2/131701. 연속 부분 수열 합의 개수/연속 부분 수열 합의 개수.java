import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> answer = new HashSet<>();
        int [] dp = new int[elements.length+1];
        for(int i=1; i<=elements.length; i++){
            //누적합만들기
            dp[i]+=dp[i-1]+elements[i-1];
            
        }
        for(int len = 1; len<=elements.length; len++){
            for(int i=0; i<=elements.length; i++){
                int last = i+len;
                if(last>=elements.length){
                    answer.add(dp[elements.length] - dp[i] + dp[last%elements.length]);
                }
                else{
                    answer.add(dp[last] - dp[i]);
                }
            }
        }
        return answer.size()-1;
    }
}