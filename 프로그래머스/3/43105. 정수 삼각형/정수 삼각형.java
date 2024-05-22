class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;
        int [][]dp = new int[height][height];
        dp[0][0]= triangle[0][0];
        for(int i=0; i<height-1; i++){
            for(int j=0; j<=i; j++){
                dp[i+1][j] =Math.max(dp[i+1][j], triangle[i+1][j]+dp[i][j]);
                dp[i+1][j+1] =Math.max(dp[i+1][j+1], triangle[i+1][j+1]+dp[i][j]);
            } 
        }
        for(int x:dp[height-1]){
            answer = Math.max(x,answer);
        }
        return answer;
    }
}