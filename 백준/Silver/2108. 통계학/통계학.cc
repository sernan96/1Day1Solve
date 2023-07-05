#include<iostream>
#include<algorithm>
#include<vector>
#include <cmath>

using namespace std;
int main() {
	int n;
	int sum = 0;
	cin >> n;
	vector<int> arr(n);
	vector<int> often_arr;
	vector<int> cnt(8001);
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		sum += arr[i];
		cnt[arr[i] + 4000]++;
	}
	int often = 0, index = 0, count = 0;
	for (int i = n; i >= 1; i--) {//빈도 수 체크
		if (find(cnt.begin(), cnt.end(), i) != cnt.end()) {
			often = i;
			break;
		}
	}
	while (index < 8001) {
		if (cnt[index] == often) {
			count++;
			often_arr.push_back(index - 4000);
		}
		index++;
	}
	sort(often_arr.begin(), often_arr.end());
	sort(arr.begin(), arr.end());
	double aver;
	aver = (double)sum /n;
	if (aver > -0.5&&aver<0) {
		cout << "0"<<"\n";
	}
	else {
		cout << round(aver) << "\n";//평균
	}
	
	cout << arr[(n - 1) / 2] << "\n";//중앙값
	if (count > 1) {
		cout << often_arr[1] << "\n"; //빈도 수
	}
	else {
		cout << often_arr[0] << "\n";
	}

	cout << arr[n - 1] - arr[0]; //M-m
}