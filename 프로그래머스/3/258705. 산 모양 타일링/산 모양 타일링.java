import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        int [] dp1 = new int [n+1];//3번 마름모
        int [] dp2 = new int [n+1];//그 외에 모양
        dp1[1] = 1;
        dp2[1] = tops[0]==0? 2:3;
        for(int i=2; i<=n; i++){
            dp1[i] = (dp1[i-1]+dp2[i-1])%10007;
            dp2[i] = tops[i-1]==0? (dp1[i-1]+2*dp2[i-1])%10007:(2*dp1[i-1]+3*dp2[i-1])%10007;
        }
        return (dp1[n]+dp2[n])%10007;
    }
}