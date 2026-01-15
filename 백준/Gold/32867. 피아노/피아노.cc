#include<iostream>
#include<algorithm>
using namespace std;
long long typedef ll;
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
		// 최대값보다 큰 경우
		if (a > min_max.second) {
			if (a - min_max.first > K) {
				answer++;
				min_max = { a, a };
			}
			else {
				min_max.second = a;
			}
		}
		//최소값보다 작은 경우
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