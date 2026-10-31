
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int n, k;
int grid[100][100];
int r, c;
int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};

int main() {
    cin >> n >> k;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> grid[i][j];
        }
    }

    cin >> r >> c;
    r--;
    c--;

    for (int t = 0; t < k; t++) {
        queue<vector<int>> q;
        vector<vector<bool>> visited(n, vector<bool>(n, false));

        int next_point[3] = {-1, -1, -1};

        q.push({r, c});
        visited[r][c] = true;

        while (!q.empty()) {
            vector<int> cur = q.front();
            q.pop();

            for (int i = 0; i < 4; i++) {
                int mx = dx[i] + cur[0];
                int my = dy[i] + cur[1];

                if (mx < 0 || mx >= n || my < 0 || my >= n)
                    continue;

                if (visited[mx][my])
                    continue;

                if (grid[r][c] <= grid[mx][my])
                    continue;

                q.push({mx, my});
                visited[mx][my] = true;

                if (next_point[2] < grid[mx][my] ||
                   (next_point[2] == grid[mx][my] &&
                   (next_point[0] > mx ||
                   (next_point[0] == mx &&
                    next_point[1] > my)))) {

                    next_point[0] = mx;
                    next_point[1] = my;
                    next_point[2] = grid[mx][my];
                }
            }
        }

        if (next_point[2] == -1)
            break;

        r = next_point[0];
        c = next_point[1];
    }

    cout << r + 1 << " " << c + 1;
    return 0;
}
