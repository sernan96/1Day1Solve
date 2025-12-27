#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N, L, R;
    int mod = 1000000007;
    cin >> N >> L >> R;
    int dp[101][101][101];
    dp[1][1][1] = 1;
    for (int i = 2; i<=N; i++) {
        for (int j = 1; j <= L; j++) {
            for (int k = 1; k <= R; k++) {
                // 추가하는 건물 i, 왼쪽에서 보이는 건물 j, 오른쪽 k
                // 현재 = 오른쪽 추가 (왼쪽 +1) + 왼쪽 추가 (오른쪽 +1) + 중간 *(중간에 놓을 수 있는 경우의 수)
                dp[i][j][k] = (dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] + (long long)dp[i - 1][j][k] * (i - 2))%mod;
            }
        }
    }
    cout << dp[N][L][R];
    return 0;
}