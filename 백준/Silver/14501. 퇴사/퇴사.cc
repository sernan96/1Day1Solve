#include <iostream>
using namespace std;

int dp[17][2] = { {0,}, };
int info[17][2] = { {0,}, };
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N;
    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> info[i][0] >> info[i][1];
    }
    for (int i = 1; i <= N+1; i++) {
        dp[i][0] = dp[i - 1][0];
        if (info[i][0] + i <= N + 1) {
            dp[i][1] = info[i][1];
        }
        for (int j = 1; j < i; j++) {
            //아무런 상담이 없었을때 전날까지의 수익 
            //만약 이전에 존재하는 상담의 끝나고 나서인 경우
            if (j + info[j][0] <= i) {
                dp[i][0] = max(dp[i][0], dp[j][1]);
                //만약 이날 상담 진행가능하면 수익 추가
                if (info[i][0] + i <= N + 1) {
                    dp[i][1] = max(dp[i][1], dp[j][1] + info[i][1]);
                }
                //이날 상담진행했을때 퇴사 이후까지 이어지는 경우
                else {
                    dp[i][1] = max(dp[i][1], dp[i - info[j][0]][1]);
                }
            }
        }
    }
    cout << max(dp[N+1][1], dp[N+1][0]);
    return 0;
}
