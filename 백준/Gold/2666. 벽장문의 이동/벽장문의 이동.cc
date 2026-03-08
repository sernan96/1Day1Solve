#include<iostream>
#include<queue>
using namespace std;

int orders[21];
int main() {
	int N, order_num;
	int opened[2];
	cin >> N;
	cin >> opened[0] >> opened[1];
	cin >> order_num;
	for (int i = 1; i <= order_num; i++) {
		cin >> orders[i];
	}
	queue<vector<int>> q;
	q.push({1, opened[0], opened[1], 0});
	int answer = 0x7fffffff;
	while (!q.empty()) {
		vector<int> cur = q.front();
		q.pop();
		int cnt = cur[0];
		int op1 = cur[1];
		int op2 = cur[2];
		int cost = cur[3];
		int dest = orders[cnt];
		//cout << cnt << "회차, " << op1 << ", " << op2 << ", cost: " << cost << endl;
		if (cnt > order_num) {
			answer = min(answer, cost);
			continue;
		}
		//op1으로 이동
		if (dest < op2) {
			q.push({ cnt + 1, dest, op2, cost + abs(op1 - dest) });
		}
		//op2로 이동
		if (dest > op1) {
			q.push({ cnt + 1, op1, dest, cost + abs(op2 - dest) });
		}
	}
	cout << answer;
	return 0;
}

/*
문제는 벽장 문을 이동시켜 최소한의 이동 횟수를 구하는 것이다

내가 생각한 방법은
BFS를 통해서 두개의 벽장이 열린 위치에 대한 상태를 저장해주고
뭘 움직였을지에 대해서 나눠서 처리해주면 될거같음
그리고 도착하면 바로 종료

상태는 {이동 카운트, 벽장 열린 위치, 가중치}

*/