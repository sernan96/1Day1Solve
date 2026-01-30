#include<iostream>
#include <algorithm>
using namespace std;

int dp[1001][2][31] = { {0,}, };
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int T, W;
	cin >> T >> W;
	for (int i = 1; i <= T; i++) {
		int plum_tree;
		cin >> plum_tree;
		//해당되는 배열에는 +1
		//아니면 그냥 불러오기만
		dp[i][1][0] = dp[i-1][1][0];
		dp[i][0][0] = dp[i-1][0][0];
		dp[i][plum_tree][0]++;
		for (int moved = 1; moved <= W; moved++) {
			if (plum_tree == 1) {
				dp[i][1][moved] = max(dp[i - 1][0][moved - 1], dp[i - 1][1][moved]) + 1;
				dp[i][0][moved] = max(dp[i - 1][1][moved - 1], dp[i - 1][0][moved]);
			}
			else {
				dp[i][1][moved] = max(dp[i - 1][0][moved - 1], dp[i - 1][1][moved]);
				dp[i][0][moved] = max(dp[i - 1][1][moved - 1], dp[i - 1][0][moved]) + 1;
			}
		}
	}
	int answer = -1;
	for (int moved = 0; moved <= W; moved++) {
		answer = max(answer, max(dp[T][0][moved], dp[T][1][moved]));
	}
	cout << answer;
	return 0;
}