#include<iostream>
#include<algorithm>
using namespace std; 
int finding(long a[], long first, long last, long ans) {
	if (first > last) {
		return 0;
	}
	if (a[(first+last)/2]==ans) {
		return 1;
	}
	else if (a[(first + last) / 2] >  ans) {
		return finding(a, first, ((first + last) / 2)-1, ans);
	}
	else {
		return finding(a, ((first + last) / 2) + 1, last, ans);
	}
}
int main() {
	int n;
	cin >> n;
	long arr[100001] = { 0, };
	long target[100001] = { 0, };
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	sort(arr, arr + n);
	int m;
	cin >> m;
	for (int k = 0; k < m; k++) {
		cin >> target[k];
	}
	for (int j = 0; j < m; j++) {
		cout<< finding(arr, 0, n-1, target[j]) << '\n';
	}
	return 0;
}