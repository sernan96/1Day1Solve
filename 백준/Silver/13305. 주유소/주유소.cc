#include<iostream>
#include<vector>
using namespace std;
int main() {
	int n;
	cin >> n;
	vector<long int> distance=vector<long int>(n - 1);
	vector<long int> oil_price = vector<long int>(n);
	for (int i = 0; i < n - 1; i++) {
		cin >> distance[i];
	}
	for (int j = 0; j < n; j++) {
		cin >> oil_price[j];
	}
	int cnt = 0, min_index=0;
	long int sum = 0, min = oil_price[0];
	//기름값을 다음것과 비교하여 더싸다면 그다음 것과 또비교
	while (cnt!=n-1) {

		if (min > oil_price[cnt]) {
			min = oil_price[cnt];
			min_index = cnt;
		}
		else {
			if (cnt != 0) {
				distance[min_index] += distance[cnt]; // 싼 가격의 도시 거리에 비싼 가격의 도시 거리를 넣어줌
				distance[cnt] = 0;
			}
		}
		cnt++;
	}
	for (int k = 0; k < n - 1; k++) {
		sum += distance[k] * oil_price[k];
	}
	cout << sum;
	return 0;
}