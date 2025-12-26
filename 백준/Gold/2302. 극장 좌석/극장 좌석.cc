#include<iostream>
#include<vector>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N, M;
	cin >> N;
	cin >> M;
	vector<int> dp(41, 0);
	dp[1] = 1;
	dp[2] = 2;
	dp[0] = 1;
	for (int i = 3; i <= 40; i++) {
		dp[i] = dp[i - 2] + dp[i - 1];
	}
	if (M == 0) {
		cout << dp[N];
		return 0;
	}
	int cnt = 1;
	int index = 0;
	for (int i = 0; i <= M; i++) {

		int cut;
		if (i == M) {
			cut = N+1;
		}
		else {
			cin >> cut;
		}
		

		cnt *= dp[cut-1 - index];
		//cout << (cut-index-1)<<"그룹 크기" << endl;
		index = cut;
	}
	cout << cnt;
	return 0;
}
/*
vip석의 사람들은 고정되어있고 나머지 사람들은 자신의 가까운 1자리까지는 바꿀 수 있다.
그럼 vip 석을 기준으로 무리를 지어
각 무리의 경우의 수를 모두 곱하면 된다.

그렇다면 무리를 지었을때 경우의 수를 어떻게 구할까?
f(x) = f(x-1)+f(x-2)
5-> 8
4-> 5
3-> 3
2-> 2
1-> 1

그럼 이제 덩어리의 크기만 구해서 vector에 넣어주고 차례대로 구해서 곱해서 결론으로 도출하면 끝

*/