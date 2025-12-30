#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

long long dp[10001][2] = { {0,}, };
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
	if (N < 3) {
		if (N == 1) {
			cout << wines[1];
			return 0;
		}
		else {
			cout << wines[1] + wines[2];
			return 0;
		}
	}
	dp[1][0] = dp[1][1] = wines[1];
	dp[2][0] = wines[2];
	dp[2][1] = dp[1][0] + wines[2];
	//dp [i][c] = max(dp[i-1][c+1] + wines[i], dp[i-2][0,1,2]) c<2;
	for (int i = 3; i <= N; i++) {
		//연속하지 않은 경우에 대해서 최신화
		dp[i][0] = max(max(dp[i - 2][0], dp[i - 2][1]) + wines[i], max(dp[i - 1][0], dp[i-1][1]));
		//연속한 경우에 대해서 
		dp[i][1] = dp[i - 1][0] + wines[i];
	}
	cout << max(dp[N][0], dp[N][1]);
	return 0;
}
/*
연속 3번은 불가능 하다
그렇다면 N+1*3크기의 dp 배열을 통해
연속으로 먹으면 [i][2]
5분
*/