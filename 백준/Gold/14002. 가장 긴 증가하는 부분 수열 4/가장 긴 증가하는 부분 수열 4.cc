#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int arr[1001] = { 0, };
int dp[1001] = { 1, };
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N;
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		dp[i] = 1;
	}
	int max_len = 1;
	for (int i = 1; i < N; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[i]>arr[j]) {
				dp[i] = max(dp[i], dp[j]+1);
			}
		}
		max_len = max(dp[i], max_len);
	}
	cout << max_len<<"\n";
	vector<int> st;
	for (int i = N - 1; i >= 0 && max_len>0; i--) {
		if (dp[i] == max_len) {
			max_len--;
			st.push_back(arr[i]);
		}
	}
	while (!st.empty()) {
		int top = st.back();
		cout << top << " ";
		st.pop_back();
	}
	return 0;
}