#include <string>
#include <vector>

using namespace std;

long long solution(int n) {
    long long answer = 0;
    int mod = 1234567;
    vector<int> dp(n+1, 0);
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;
    for(int i=3; i<=n; i++){
        dp[i] = (dp[i-2]+dp[i-1])%mod;
    }
    return dp[n];
}