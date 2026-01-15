#include<iostream>
#include<algorithm>
using namespace std;
long long typedef ll;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	ll N, K;
	cin >> N;
	cin >> K;
	ll left = 1;
	ll right = N * N;
	ll mid = (left + right) / 2;
	ll answer = 0;
	while (left <= right) {
		ll cnt = 0;
		mid = (left + right) / 2;
		for (ll i = 1; i <= N; i++) {
			cnt += min(N, mid / i);
		}
		if (cnt >= K) {
			answer = mid;
			right = mid - 1;
		}
		else {
			left = mid + 1;
		}
	}
	cout << answer;
	return 0;
}