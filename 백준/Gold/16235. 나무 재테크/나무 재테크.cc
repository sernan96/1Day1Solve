#include<iostream>
#include<vector>
#include<queue>
using namespace std;

struct cmp {
	bool operator()(int a, int b) {
		return a < b;
	}
};

int alive_tree, N;
bool is_first;
vector<vector<int>> nutrition_map, add_nutrition_map;
vector<vector<deque<int>>> trees_map;
void eat_nutrition(int x, int y);
void get_child_tree(int x, int y);
int main() {
	int M, K;
	cin >> N >> M >> K;
	nutrition_map.assign(N + 1, vector<int>(N + 1, 5));
	add_nutrition_map.assign(N + 1, vector<int>(N + 1, 0));
	trees_map.assign(N + 1, vector<deque<int>>(N + 1, deque<int>()));
	//양분 정보 저장
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> add_nutrition_map[i][j];
		}
	}
	alive_tree = 0;
	is_first = true;
	for (int i = 0; i < M; i++) {
		int x, y, z;
		cin >> x >> y >> z;
		trees_map[x][y].push_back(z);
		alive_tree++;
	}
	while (K--) {
		// 봄, 여름, 겨울 : 양분 먹기(나이 증가), 양분 부족시 죽음, 전년도 양분 뿌리기(첫해가 아닌 경우)
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				//봄 여름 동시
				eat_nutrition(i, j);
			}
		}
		if (is_first) {
			is_first = false;
		}
		// 가을
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				//번식
				get_child_tree(i, j);
			}
		}
	}
	cout << alive_tree;
	return 0;
}

void eat_nutrition(int x, int y) {
	//전년도 거름 뿌리기 (첫해가 아닌 경우) -> 연산 1회라도 줄이기 위해서
	if (!is_first) {
		nutrition_map[x][y] += add_nutrition_map[x][y];
	}
	//해당 칸에 위치한 나무들에게 어린 순서대로 양분을 먹임
	vector<int> after_eat_trees;
	int dead_tree = 0;
	while (!trees_map[x][y].empty()) {
		int tree = trees_map[x][y].front();
		trees_map[x][y].pop_front();
		//양분 섭취가능
		if (tree <= nutrition_map[x][y]) {
			nutrition_map[x][y] -= tree;
			after_eat_trees.push_back((tree + 1));
		}
		//불가능 -> 나무 죽음
		else {
			dead_tree += tree / 2;
			alive_tree--;
		}
	}
	nutrition_map[x][y] += dead_tree;
	//양분 섭취 끝난 나무 반영
	for (int tree: after_eat_trees) {
		trees_map[x][y].push_back(tree);
	}
}

bool can_go(int x, int y){
	return x >= 1 && y >= 1 && x <= N && y <= N;
}

int dx[] = {1, 0, -1, 0, 1, 1, -1, -1};
int dy[] = {0, 1, 0, -1, 1, -1, 1, -1};
void get_child_tree(int x, int y) {
	for (int tree: trees_map[x][y]) {
		if (tree % 5 == 0) {
			for (int dir = 0; dir < 8; dir++) {
				int mx = x + dx[dir];
				int my = y + dy[dir];
				if (can_go(mx, my)) {
					trees_map[mx][my].push_front(1);
					alive_tree++;
				}
			}
		}
	}
}