#include<iostream>
#include<queue>
#include<algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N;
	cin >> N;
	priority_queue<int, vector<int>, greater<int>> pq;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			int x;
			cin >> x;
			if (pq.size() < N) {
				pq.push(x);
			}
			else if ( x > pq.top()) {
				pq.pop();
				pq.push(x);
			}
		};
	}
	cout << pq.top();
	return 0;
}
/*
상위 N개의 수만 저장하여 정렬하고 
만약 상위 N번째의 숫자보다 작으면 그냥 넘어가고
만약 크면 하나 빼주고 푸쉬해줌
*/