
#include <iostream>
#include <algorithm>
using namespace std;

int n;
int board[21][21];
int dir[21][21];
int dp[21][21];

// 1: 상, 2: 우상, 3: 우, 4: 우하
// 5: 하, 6: 좌하, 7: 좌, 8: 좌상
int dx[8] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dy[8] = {0, 1, 1, 1, 0, -1, -1, -1};

int dfs(int x, int y) {
    if (dp[x][y] != -1)
        return dp[x][y];

    dp[x][y] = 0;

    int d = dir[x][y] - 1;
    int nx = x + dx[d];
    int ny = y + dy[d];

    while (nx >= 0 && nx < n && ny >= 0 && ny < n) {
        if (board[nx][ny] > board[x][y]) {
            dp[x][y] = max(dp[x][y], 1 + dfs(nx, ny));
        }

        nx += dx[d];
        ny += dy[d];
    }

    return dp[x][y];
}

int main() {
    cin >> n;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> board[i][j];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> dir[i][j];

    int r, c;
    cin >> r >> c;

    fill(&dp[0][0], &dp[0][0] + 21 * 21, -1);

    cout << dfs(r - 1, c - 1);

    return 0;
}
