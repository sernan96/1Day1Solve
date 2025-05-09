import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int mod = 1000000007;
        int [][] map = new int [n+1][m+1];
        int [][] dp = new int[n+1][m+1];
        
        dp[1][1] = 1;
        for(int [] x: puddles){
            map[x[1]][x[0]] = -1;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if((i==1&&j==1)||map[i][j]==-1){
                System.out.print(0+" ");
                    continue;
                }
                int upper = map[i-1][j]!=-1?dp[i-1][j]:0;
                int left = map[i][j-1]!=-1?dp[i][j-1]:0;
                dp[i][j]= (upper%mod + left%mod)%mod;
            }
        }
        return dp[n][m];
    }
}
/*
단지 위와 왼쪽에서 온경우의 수를 합쳐서 모듈러 연산을 해주면 끝

*/