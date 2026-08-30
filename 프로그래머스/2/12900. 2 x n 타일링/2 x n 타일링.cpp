#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int n) {
    int answer = 0;
    int mod = 1000000007;
    vector<int> dp(n+1, 0);
    dp[1]=1;
    dp[2]=2;
    for(int i=3; i<=n; i++){
        dp[i] = (dp[i]+dp[i-1])%mod;
        dp[i] = (dp[i]+dp[i-2])%mod;
    }
    return dp[n]%mod;
}