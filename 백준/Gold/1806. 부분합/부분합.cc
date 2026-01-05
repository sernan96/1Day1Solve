#include<iostream>
#include<vector>
#include<algorithm>
#include<cstring>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int	N, S;
	cin >> N >> S;
	vector<int> arr(N + 1, 0);
	vector<long long int> sum(N + 1, 0);
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	for (int j = 1; j <= N; j++) {
		sum[j] = sum[j - 1] + arr[j];
	}
	int INF_MAX = 0x7f7f7f7f;
	int len = INF_MAX;
	for (int i = 1; i <= N; i++) {
		int left = 0;
		int right = i;
		int mid = (left + right) / 2;
		while (left<=right) {

			int mid = (left + right) / 2;
			long long int summary = sum[i] - sum[mid];
			if (summary >= S) {
				len = min(len, i - mid);
				left = mid + 1;
			}
			else if (summary < S) {
				right = mid - 1;
			}
		}
	}
	int answer = len == INF_MAX ? 0 : len;
	cout << answer;
	return 0;
}
/*
단순 중첩 반복문으로 진행을 하면 
최악의 경우 N*N 으로 시간초과가 난다.
그렇기에
누적합 배열을 구하고 
순회를 하며 
i번째 누적합에서 i보다 작은 index에서
sum[i]-sum[j]가 S이상인 경우를 이분탐색으로 찾는다.
그럼 NlogN으로 시간초과에서 안심가능
*/