#include<iostream>
using namespace std;
int main() {
	int n, k, coin = 0, value[10] = { 0, };
	cin >> n >> k;
	for (int i = 0; i < n; i++) {
		cin >> value[i];
	}
	for (int j = n-1; j >= 0; j--) {
		if (k / value[j] >= 1) {
			coin += k / value[j];
			k = k % value[j];
		}
	}
	cout << coin;
	return 0;
}