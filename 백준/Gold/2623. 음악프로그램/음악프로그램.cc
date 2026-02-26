#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int N, M;
	cin >> N >> M;

	vector<vector<int>> graph(N+1, vector<int>());
	vector<int> front(N + 1, 0);
	vector < bool > visited(N + 1, false);


	for (int i = 0; i < M; i++) {
		int singer_num;
		cin >> singer_num;
		vector<int> singers(singer_num, 0);
		for (int j = 0; j < singer_num; j++) {
			cin >> singers[j];
			if (j != 0) {
				for (int k = 0; k < j; k++) {
					graph[singers[k]].push_back(singers[j]);
					front[singers[j]]++;
				}
			}
		}
	}
	queue<int> q;
	for (int i = 1; i <= N; i++) {
		if (front[i]==0) {
			q.push(i);
			visited[i] = true;
		}
	}
	vector<int> result;
	while (!q.empty()) {
		int cur = q.front();
		q.pop();
		result.push_back(cur);
		for (int x : graph[cur]) {
			if (visited[x]) {
				continue;
			}
			front[x]--;
			if (front[x] == 0) {
				q.push(x);
				visited[x] = true;
			}
		}
	}
	if (result.size() != N) {
		cout << 0;
		return 0;
	}
	for (int i = 0; i < N; i++) {
		cout << result[i] << "\n";
	}
	return 0;
}
/*
입력 받는 수에 대해서 각각 front ++ 를 해주어 
앞서 존재해야하는 노드의 수에 대해서 갱신을 해준다. 

그런데 여기서 주의할 점은 불가능한 순서에 대해서 어떻게 파악할지이다.

내아이디어는 이러하다 
각 보조 PD가 정한 순서에 대해서 단방향 그래프를 구성해주는데
만약 그래프에서 사이클이 발생하면 종료되게 된다.

*/
