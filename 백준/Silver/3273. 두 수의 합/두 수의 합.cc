#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int main() {
	int n, x;
	cin >> n;
	vector<int>arr(n);//n크기의 int배열 생성
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	cin >> x;
	sort(arr.begin(), arr.end());
	int cnt = 0;
	int left = 0; // 배열의 왼쪽 인덱스
	int right = n - 1; // 배열의 오른쪽 인덱스

	while (left < right) {
		int sum = arr[left] + arr[right];
		if (sum == x) {
			cnt++;
			left++;
			right--;
		}
		else if (sum < x) {
			left++;
		}
		else { // sum > x
			right--;
		}
	}


	cout << cnt;
}