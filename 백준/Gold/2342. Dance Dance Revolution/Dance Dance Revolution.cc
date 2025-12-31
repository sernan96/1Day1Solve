#include<iostream>
#include<vector>
#include<algorithm>
#include<cstring>
using namespace std;

int dp[100001][5][5];
int cost[5][5] = {
	{0, 2, 2, 2, 2},
	{2, 1, 3, 4, 3},
	{2, 3, 1, 3, 4},
	{2, 4, 3, 1, 3},
	{2, 3, 4, 3, 1} };
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int num;
	vector<int> v;
	v.push_back(0);
	cin >> num;
	v.push_back(num);
	while (num != 0) {
		cin >> num;
		if (num == 0) {
			break;
		}
		v.push_back(num);
	}
	int N = v.size()-1;
	memset(dp, 0x7f7f7f7f, sizeof(dp));
	dp[1][v[1]][0] = 2;
	dp[1][0][v[1]] = 2;

	for (int i = 1; i <= N; i++) {
		for (int other = 0; other <= 4; other++) {
			//이전발 들이 연속으로 원하는 지점 
			if (v[i - 1] != other && other != v[i]) {
				//왼발이 이동한 경우 
				dp[i][v[i]][other] = min(dp[i - 1][v[i - 1]][other] + cost[v[i - 1]][v[i]], dp[i][v[i]][other]);
				//오른발이 이동
				dp[i][other][v[i]] = min(dp[i - 1][other][v[i - 1]] + cost[v[i - 1]][v[i]], dp[i][other][v[i]]);
			}
			// 다른 발이 v[i-1]찍고 남은 발로 v[i]를 찍은 경우 
			if (v[i - 1] != v[i] && other != v[i - 1]) {
				//왼발이 이동
				dp[i][v[i]][v[i - 1]] = min(dp[i - 1][other][v[i - 1]] + cost[other][v[i]], dp[i][v[i]][v[i - 1]]);
				//오른발이 이동
				dp[i][v[i - 1]][v[i]] = min(dp[i - 1][v[i - 1]][other] + cost[other][v[i]], dp[i][v[i - 1]][v[i]]);
			}

		}
	}
	int answer = 0x7f7f7f7f;
	for (int left = 0; left <= 4; left++) {
		for (int right = 0; right <= 4; right++) {
			answer = min(dp[N][left][right], answer);
		}
	}
	cout << answer;
	return 0;
}
/*
dp배열을 3차원으로 만들어 주고
dp[i번째 수열(밟아야 하는 칸index)][왼발 위치][오른발 위치] = min(왼발로 밟는 경우, 오른발로 밟는 경우)
왼발에서 이동해왔는지 오른발에서 이동해왔는지도 고려
*/