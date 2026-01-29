#include<iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef long long ll;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N;
	cin >> N;

	vector<ll> arr(N, 0);
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	sort(arr.begin(), arr.end());

	vector<ll> answers(3, 0);
	ll answer = 0x7fffffffffffffff;
	for (int i = 0; i < N - 2; i++) {
		int left = i + 1;
		int right = N - 1;
		while (left < right) {
			ll sum = arr[i] + arr[left] + arr[right];
			if (sum == 0) {
				cout << arr[i] << " " << arr[left] << " " << arr[right];
				return 0;
			}
			if (abs(sum) < answer) {
				answer = abs(sum);
				answers = { arr[i], arr[left], arr[right] };
			}
			if (sum<0) {
				left++;
			}
			else {
				right--;
			}
		}
	}

	cout << answers[0] << " " << answers[1] << " " << answers[2];
	return 0;
}
/*
하나를 선택하고 투포인터로 특성값의 합을 0에 가깝게 만들어준다.
만약 해당 특성값이 0에서 제일 가깝다면 정답 배열에 저장해준다.
*/