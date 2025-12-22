#include<iostream>
#include<vector>
#include<algorithm>
#include <queue>
using namespace std;
static int INF_MAX = 210000000;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N, Q;
	cin >> N >> Q;
	vector<vector<pair<int, int>>> usado(N + 1);

	for (int i = 0; i < N-1; i++) {
		int p, q, r;
		cin >> p >> q >> r;
		usado[p].push_back({ q,r });
		usado[q].push_back({ p,r });
	}

	for (int i = 0; i < Q; i++) {
		int k, v;
		cin >> k >> v;
		//BFS를 통해서 이번에 몇개의 동영상을 추천할지 고르기
		queue<int> q;
		vector < bool> visited(N+1, false);
		visited[v] = true;
		int cnt = 0;
		for (pair<int, int> edge : usado[v]) {
			if (edge.second<k) {
				continue;
			}
			q.push(edge.first);
			visited[edge.first] = true;
		}
		while (!q.empty()) {
			int cur = q.front();
			q.pop();
			cnt++;
			for (pair<int, int> nextEdge: usado[cur]) {
				int next = nextEdge.first;
				if (!visited[next] && k<=nextEdge.second) {
					visited[next] = true;
					q.push({next});
				}
			}
		}
		cout << cnt << "\n";
	}
	return 0;
}

/*
USADO가 K 이상인 동영상은 
해당 지점까지 가는데에 간선의 최대값이 K보다 커야한다는 뜻
X플로이드 워셜 알고리즘으로
출발 노드에서 도착 노드까지의 USADO 최대값을 저장해두면
Q가 아무리 많이도 불러오기만 하면 된다.
그럼 k값과 출발 노드가 주어졌을때 해당 출발 노드에서
도착할 수 있는 노드 중에 방문 못하는 노드를 제외한 노드중 k보다
USADO값이 큰 경우를 카운팅 해서 출력해주면 된다.

BFS로 가다가 K보다 큰 경우만 추가해주다가 K보다 작은 경우를 만나면 종료
*/