#include<iostream>
#include<queue>
#include<map>
#include<set>
using namespace std;

int map_arr[1001][1001] = { {0,}, };
int group_arr[1001][1001] = { {0,}, };
int result[1001][1001] = { {0,}, };
bool visited[1001][1001] = { {false,}, };
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int N = 0, M = 0;
int group = 0;
map<int, int> m;

bool can_go(int x, int y) {
	return x >= 0 && y >= 0 && x < N && y < M;
}

void bfs(int x, int y) {
	queue<pair<int, int>> q;
	queue<pair<int, int>> visited_loc;
	q.push({x, y});
	visited[x][y] = true;
	int area = 0;
	while (!q.empty()) {
		pair<int, int> cur = q.front();
		q.pop();
		area++;
		visited_loc.push({ cur.first, cur.second });
		for (int dir = 0; dir < 4; dir++) {
			int mx = cur.first + dx[dir];
			int my = cur.second + dy[dir];
			if (can_go(mx, my) && !visited[mx][my] && map_arr[mx][my] == 0) {
				q.push({ mx, my });
				visited[mx][my] = true;
			}
		}
	}
	while (!visited_loc.empty()) {
		pair<int, int> cur = visited_loc.front();
		visited_loc.pop();
		group_arr[cur.first][cur.second] = group;
	}
	m[group] = area;
	group++;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);	
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string input;
		cin >> input;
		for (int j = 0; j < M; j++) {
			map_arr[i][j] = input.at(j) - '0';
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (!visited[i][j] && map_arr[i][j] == 0) {
				bfs(i, j);
			}
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map_arr[i][j] == 1) {
				int sum = 1;
				set<int> groups;
				for (int dir = 0; dir < 4; dir++) {
					int mx = i + dx[dir];
					int my = j + dy[dir];
					if (can_go(mx, my) && map_arr[mx][my]==0) {
						groups.insert(group_arr[mx][my]);
					}
				}
				for (int x: groups) {
					sum += m[x];
				}
				sum %= 10;
				result[i][j] = sum;
			}
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << result[i][j];
		}
		cout << "\n";
	}
	return 0;
}
/*
각 벽으로 부터 시작해서 BFS를 돌았을때 몇개의 영역을 지니는지에 대해서 계산해준다.
그런데 그렇게 하면 BFS를 최대 100만번해야하고
그렇게 했을때는 시간초과가 날 것이라고 생각이된다.
그렇기 때문에 각 0으로 이루어진 영역에 대해서 넓이를 구해주고 
해당 영역을 전부 해당 너비를 적어준다.
그럼 나중에 벽에 대해서 구할때
상하좌우 합을 구해서 10으로 나눠서 return해주면 된다.

영역을 구해주는데 해당 BFS에서 구한 영역의 넓이를 
방문한 큐에 pair값을 순회하며 전부 해당 넓이를 표시해준다.
*/