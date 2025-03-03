class Solution {
    int MOD = 20170805;
    static int [][] map, dp;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        map = cityMap;
        dp = new int[m][n];
        dp[0][0]=1;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i+j!=0 && map[i][j]==0){
                    if(i==0){ //가장 윗줄 우측 또는 아래로만 이동가능 == 왼쪽만 보면됨
                        if(map[i][j-1]==2){ // 이전지점이 회전불가
                            dp[i][j] = findPre(i, j-1, 1);   
                        }
                        else if(map[i][j-1]==1){ //이전지점이 통행불가
                            dp[i][j]=0;
                        }
                        else{// 아무런 제약사항 없는 경우
                            dp[i][j] = dp[i][j-1];
                        }
                    }
                    else if(j==0){//제일 좌측에서 내려가는 경우만 가능 == 위쪽만 보면됨
                        if(map[i-1][j]==2){ // 이전지점이 회전불가
                            dp[i][j] = findPre(i-1, j, 0);   
                        }
                        else if(map[i-1][j]==1){ //이전지점이 통행불가
                            dp[i][j]=0;
                        }
                        else{// 아무런 제약사항 없는 경우
                            dp[i][j] = dp[i-1][j];
                        }
                    }
                    else{//위와 좌측을 전부 비교해야 하는 경우
                        //사실 이전 지점이 회전불가능 가능인지만 보면됨
                        int up = 0;
                        int left = 0;
                        if(map[i-1][j]==2){
                            up = findPre(i-1, j, 0);
                            //System.out.println("현재: "+i+", "+j+" 위 검출: "+up);
                        }
                        else{
                            up = dp[i-1][j];    
                        }
                        if(map[i][j-1]==2){
                            left = findPre(i, j-1, 1);
                            //System.out.println("현재: "+i+", "+j+" 옆 검출: "+left);
                        }
                        else{
                            left = dp[i][j-1];    
                        }
                        dp[i][j] = (up+left)%MOD;
                    }
                }
            }
        }
        return dp[m-1][n-1];
    }
    static int findPre (int x, int y, int dir){//세로(0)인지 가로(1)인지에 따라 다름
        if(dir ==1){//가로
            if(y<0){
                return 0;
            }
            else if(map[x][y]==0){
                return dp[x][y];
            }
            else if(map[x][y]==2){
                return findPre(x, y-1, dir);    
            }        
        }
        else{//세로
            if(x<0){
                return 0;
            }
            else if(map[x][y]==0){
                return dp[x][y];   
            }
            else if(map[x][y]==2){
                return findPre(x-1, y, dir);    
            }
        }
        return 0;
    }
}