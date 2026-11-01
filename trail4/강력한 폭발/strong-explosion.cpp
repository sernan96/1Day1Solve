
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, answer = 0, cnt = 0;
vector<pair<int, int>> bombs;
vector<vector<int>> visited;

// 폭탄 3종류의 방향
int dx[3][5] = {
    {0, -2, -1, 1, 2},  // 세로
    {0, -1, 1, 0, 0},   // 십자
    {0, -1, -1, 1, 1}   // X자
};

int dy[3][5] = {
    {0, 0, 0, 0, 0},
    {0, 0, 0, -1, 1},
    {0, -1, 1, -1, 1}
};

bool inRange(int x, int y) {
    return x >= 0 && x < n && y >= 0 && y < n;
}

// delta = 1: 폭탄 설치
// delta = -1: 폭탄 복구
void change(int x, int y, int type, int delta) {
    for (int i = 0; i < 5; i++) {
        int nx = x + dx[type][i];
        int ny = y + dy[type][i];

        if (!inRange(nx, ny)) continue;

        if (delta == 1 && visited[nx][ny] == 0)
            cnt++;

        if (delta == -1 && visited[nx][ny] == 1)
            cnt--;

        visited[nx][ny] += delta;
    }
}

void dfs(int depth) {
    if (depth == bombs.size()) {
        answer = max(answer, cnt);
        return;
    }

    auto [x, y] = bombs[depth];

    for (int type = 0; type < 3; type++) {
        change(x, y, type, 1);
        dfs(depth + 1);
        change(x, y, type, -1);
    }
}

int main() {

    cin >> n;
    visited.assign(n, vector<int>(n, 0));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int x;
            cin >> x;

            if (x == 1)
                bombs.push_back({i, j});
        }
    }

    dfs(0);

    cout << answer << '\n';

    return 0;
}
