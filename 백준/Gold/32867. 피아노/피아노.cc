#include<iostream>
#include<algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N, K;
	cin >> N >> K;
	pair<int, int> min_max;
	int first_num;
	cin >> first_num;

	min_max.first = first_num;
	min_max.second = first_num;
	
	int answer = 0;
	N--;
	K--;
	while (N-- != 0) {
		int a;
		cin >> a;
		if (a > min_max.second) {
			if (a - min_max.first > K) {
				answer++;
				min_max = { a, a };
			}
			else {
				min_max.second = a;
			}
		}
		else if (a < min_max.first) {
			if (min_max.second - a > K) {
				answer++;
				min_max = { a, a };
			}
			else {
				min_max.first = a;
			}
		}
	}
	cout << answer;
	return 0;
}