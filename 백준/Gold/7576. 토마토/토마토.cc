#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int N, M;
bool can_go(int x, int y) {
	return x >= 0 && y >= 0 && x < N && y < M;
}
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	cin >> M >> N;
	vector<vector<int>> tomato(N, vector<int>(M, 0));
	vector<vector<int>> grown;
	int cnt = 0;
	int wall = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> tomato[i][j];
			if (tomato[i][j] == 1) {
				grown.push_back({i, j});
				cnt++;
			}
			else if (tomato[i][j] == -1) {
				wall++;
			}
		}
	}
	int T;
	bool flag = true;
	int dx[] = { 1, 0, -1, 0 };
	int dy[] = { 0, 1, 0, -1 };
	for (T = 0; ;T++) {
		flag = false;
		vector<vector<int>> new_grown;
		for (vector<int> start : grown) {
			queue<vector<int>> q;
			q.push({start[0], start[1]});
			while (!q.empty()) {
				vector<int> cur = q.front();
				q.pop();
				for (int dir = 0; dir < 4; dir++) {
					int mx = cur[0] + dx[dir];
					int my = cur[1] + dy[dir];
					if (can_go(mx, my) && tomato[mx][my]==0) {
						flag = true;
						cnt++;
						tomato[mx][my] = 1;
						new_grown.push_back({mx, my});
					}
				}
			}
		}
		grown.clear();
		grown = new_grown;
		if (!flag) {
			break;
		}
	}
	if (cnt== N*M-wall) {
		cout << T;
	}
	else {
		cout << -1;
	}
	return 0;
}
/*

*/