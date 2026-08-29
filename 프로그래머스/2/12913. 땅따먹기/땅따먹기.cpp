#include <algorithm>
#include <vector>
#include<iostream>

using namespace std;

int solution(vector<vector<int> > land)
{
    int answer = 0;
    
    int N = land.size();
    
    vector<vector<int> > dp(N+1, vector<int>(4, 0));
    for(int i = 0; i<4; i++){
        dp[0][i] = land[0][i];
    }
    for(int i=1; i<=N; i++){
        if(i==N){
            for(int j=0; j<4; j++){
                answer = max(answer, dp[N-1][j]);
            }
        }
        else{
            for(int j=0; j<4; j++){//현재
                for(int k=0; k<4; k++){//전 행
                    if(j!=k){
                        dp[i][j] = max(dp[i-1][k]+land[i][j], dp[i][j]);
                    }
                }
            }
        }
    }
    
    return answer;
}