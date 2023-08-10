#include<iostream>
#include<algorithm>
using namespace std;
int main() {
	int n, p[1000] = { 0, }, min = 0, count =0;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> p[i];
	}
	sort(p, p+n);
	for (int k = n; k > 0; k--) {
		min += p[count]*k;
		count++;
	}
	cout << min;
	return 0;
}