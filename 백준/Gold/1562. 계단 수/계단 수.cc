#include <iostream>
#include <vector>
#include <cstring>
#define MOD 1000000000
using namespace std;

int dp[101][10][0x400];
int bit_max = 0x3FF;

int get_dp(int n, int before, int visit);

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N;
    cin >> N;
    memset(dp, -1, sizeof(dp));
    
    int sum = 0;

    for (int i = 1; i <= 9; i++) {
        sum = (sum + get_dp(N-1, i, (1<<i))) % MOD;
    }

    cout << sum;

    return 0;
}
int get_dp(int n, int before, int visited) {
    //종료 규칙 N부터 시작해서 0까지 0일 시에 전부 방문했는지 visited 체크 후 1 또는 0 return
    if (n == 0) {
        return visited == bit_max;
    }
    if (dp[n][before][visited]!=-1) {
        return dp[n][before][visited];
    }
    int sum = 0;
    if (before - 1 >= 0) {
        sum = (sum + get_dp(n - 1, before - 1, visited | (1 << before-1))) % MOD;
    }
    if (before + 1 < 10) {
        sum = (sum + get_dp(n - 1, before + 1, visited | (1 << before+1))) % MOD;
    }
    dp[n][before][visited] = sum;
    return sum;
}