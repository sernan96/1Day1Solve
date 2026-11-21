#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	//FSGUD
	int F, S, G, U, D;
	cin >> F >> S >> G >> U >> D;
	vector<int> visited(F + 1, 1e6 + 1);
	queue<vector<int>> q;
	q.push({S, 0});
	visited[S] = 0;

	while (!q.empty()) {
		vector<int> cur = q.front();
		q.pop();
		//cout << cur[0] << "층 입니다." << endl;
		if (cur[0] == G) {
			cout << cur[1];
			return 0;
		}

		//Up
		if (cur[0] + U <= F && visited[cur[0] + U] > cur[1] + 1) {
			q.push({ cur[0] + U, cur[1] + 1 });
			visited[cur[0] + U] = cur[1] + 1;
		}
		//Down
		if (cur[0] - D > 0 && visited[cur[0] - D] > cur[1] + 1) {
			q.push({ cur[0] - D, cur[1] + 1 });
			visited[cur[0] - D] = cur[1] + 1;
		}
	}
	cout << "use the stairs";
	
	return 0;
}