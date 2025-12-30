#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int dp[10001][2] = { {0,}, };
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N;
	cin >> N;
	vector<int> wines(N + 1, 0);

	for (int i = 1; i <= N; i++) {
		cin >> wines[i];
	}
	//3 이하인 경우는 따로 처리
	if (N < 3) {
		int result = N == 1 ? wines[1] : wines[1] + wines[2];
		cout << result;
		return 0;
	}
	// 초기값 세팅
	dp[1][0] = dp[1][1] = wines[1]; // 이전 값이 없기때문에 i=3에서 i=1인 dp값을 불러올 상황 대비
	dp[2][0] = wines[2];
	dp[2][1] = dp[1][0] + wines[2];

	for (int i = 3; i <= N; i++) {
		//연속하지 않은 경우에 대해서 최신화
		dp[i][0] = max(max(dp[i - 2][0], dp[i - 2][1]) + wines[i], max(dp[i - 1][0], dp[i-1][1]));
		//연속한 경우에 대해서 
		dp[i][1] = dp[i - 1][0] + wines[i];
	}
	cout << max(dp[N][0], dp[N][1]);
	return 0;
}