
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 0x3f3f3f3f;

int n;
int A[11][11];
vector<vector<int>> dp;

// 현재 도시 cur, 방문한 도시 visited
int TSP(int cur, int visited) {

    // 모든 도시를 방문했다면
    if (visited == (1 << (n+1)) - 2) {
        if (A[cur][1] != 0)
            return A[cur][1];

        return INF;
    }

    // 이미 계산한 상태라면 재사용
    if (dp[cur][visited] != -1)
        return dp[cur][visited];

    int &ret = dp[cur][visited];
    ret = INF;

    // 다음 도시 탐색
    for (int i = 2; i <= n; i++) {

        // 이미 방문했다면
        if (visited & (1 << i))
            continue;

        // 갈 수 없는 도시라면
        if (A[cur][i] == 0)
            continue;

        int next = visited | (1 << i);

        ret = min(ret, A[cur][i] + TSP(i, next));
    }

    return ret;
}

int main() {
    cin >> n;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> A[i][j];
        }
    }

    dp.assign(n+1, vector<int>(1 << (n+1), -1));

    cout << TSP(1, 1 << 1);

    return 0;
}
