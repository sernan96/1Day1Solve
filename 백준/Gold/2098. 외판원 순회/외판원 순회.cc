#include<iostream>
#include<vector>
#include<algorithm>
#include<cstring>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int N;
	int INF_MAX = 0x7f7f7f7f;
	cin >> N;

	int N_MAX = (1 << N) - 1;
	
	vector<vector<int>> dp(1 << N, vector<int>(N, INF_MAX));
	vector<vector<int>> W(N, vector<int>(N, 0));

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> W[i][j];
		}
	}

	dp[1 << 0][0] = 0;
	for (int mask = 0; mask < (1 << N); mask++) {
		for (int cur = 0; cur < N; cur++) {
			if (dp[mask][cur] == INF_MAX || (mask & (1 << cur)) == 0) {
				continue;
			}
			for (int next = 0; next < N; next++) {
				if (W[cur][next] == 0 || (mask & (1 << next)) != 0) {
					continue;
				}
				int visited = mask | (1 << next);
				dp[visited][next] = min(dp[visited][next], dp[mask][cur] + W[cur][next]);
			}
		}
	}
	int answer = INF_MAX;
	for (int cur = 0; cur < N; cur++) {
		if (W[cur][0] == 0 || dp[N_MAX][cur] == INF_MAX) {
			continue;
		}
		answer = min(answer, dp[N_MAX][cur] + W[cur][0]);
	}
	cout << answer;
	return 0;
}
/*
전부 다 돌고 돌아오는 경우 중에서 최소의 비용으로 모든 도시들을 찍고 돌아올 때의 비용을 구해야함.

*/