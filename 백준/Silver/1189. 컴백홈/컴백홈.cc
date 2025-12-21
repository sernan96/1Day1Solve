#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

static int answer, R, C, K;
static int dx[] = { 1, 0, -1, 0 };
static int dy[] = { 0, -1, 0, 1 };
static vector<vector<int>> map;
static vector<vector<bool>> visited;

static void DFS(int x, int y, int depth);
static bool canGo(int x, int y);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	answer = 0; 
	cin >> R >> C >> K;
	map.assign(R, vector<int>(C, 0));
	visited.assign(R, vector<bool>(C, false));
	for (int i = 0; i < R; i++) {
		string input;
		cin >> input;
		for (int j = 0; j < C; j++) {
			map[i][j] = input.at(j) == 'T' ? 1 : 0;
		}
	}
	visited[R - 1][0] = true;
	DFS(R-1, 0 , 1);
	cout << answer;
	return 0;
}

static bool canGo(int x, int y) {
	return x >= 0 && y >= 0 && x < R && y < C;
}
static void DFS(int x, int y, int depth) {
	if (depth > K) {
		return;
	}
	else if (depth==K && x==0 && y==C-1) {
		answer++;
		return;
	}
	for (int dir = 0; dir < 4; dir++) {
		int mx = x + dx[dir];
		int my = y + dy[dir];
		if (canGo(mx, my) && !visited[mx][my] && map[mx][my]==0) {
			visited[mx][my] = true;
			DFS(mx, my, depth + 1);
			visited[mx][my] = false;
		}
	}
}


/*
DFS로 거리가 K인 경우의 가짓수를 구해서 출력해준다.
재귀함수의 처음에서 도착했고 depth가 K와 같다면 answer++ and return
K보다 Depth가 크다면 도착해도 의미없으니 종료
*/