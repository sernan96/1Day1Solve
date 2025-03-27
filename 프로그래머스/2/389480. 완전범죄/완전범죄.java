class Solution {
    public int solution(int[][] info, int n, int m) {
        int total = 0;
        for(int i=0; i<info.length; i++){
            total+=info[i][0];
        }
        int [][] dp = new int [info.length+1][m];
        for(int i=1; i<m; i++){
            for(int j=1; j<=info.length; j++){
                if(info[j-1][1]<=i && dp[j-1][i]<=dp[j-1][i-info[j-1][1]]+info[j-1][0]){
                    dp[j][i] =dp[j-1][i-info[j-1][1]]+info[j-1][0];
                }
                else{
                    dp[j][i] =dp[j-1][i];
                }
            }
        }
        int result = total - dp[info.length][m-1];
        if(result>=n){
            return -1;
        }
        else if(result<0){
            return 0;
        }
        return result;  
    }
}