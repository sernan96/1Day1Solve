#include<iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int dp[1001][10] = { {0,}, };
	int N;
	cin >> N; 
	for (int i = 0; i < 10; i++) {
		dp[1][i] = 1;
	}
	for (int i = 2; i <= N; i++) {
		for (int j = 0; j < 10; j++) {
			for (int k = j; k < 10; k++) {
				dp[i][k] = (dp[i][k] + dp[i-1][j]) % 10007;
			}
		}
	}
	int sum = 0;
	for (int i = 0; i < 10; i++) {
		sum = (sum + dp[N][i]) % 10007;
	}
	cout << sum;
	return 0;
}
/*
i자리 수보다 큰 에 대해서 i+1에 모두 + 해준다.
*/