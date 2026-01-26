#include<iostream>
#include<vector>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N;
	cin >> N;
	vector<int> arr(N, 0);
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	int answer = 0x7f7f7f7f;
	for (int i = 0; i < N-1; i++) {
		int left = i+1;
		int right = N - 1;
		int mid = (left + right) / 2;
		while (left <= right) {
			mid = (left + right) / 2;
			int sum = arr[mid] + arr[i];
			//cout << arr[i] << " + " << arr[mid] << " = " << gap << endl;
			//합이 만약 음수라면 left = mid + 1
			//합이 양수라면 right  = mid - 1
			if(sum==0) {
				cout << 0;
				return 0;
			}
			else if (sum<0) {
				answer = abs(sum) < abs(answer) ? sum : answer;
				left = mid + 1;
			}
			else {
				answer = abs(sum) < abs(answer) ? sum : answer;
				right = mid - 1;
			}
		}
	}
	cout << answer;
	return 0;
}
/*
이분탐색으로 하게 된다면
NlogN의 방식으로
각 용액과 합쳤을때 
0에 가까운 값을 찾아내면 된다.

*/