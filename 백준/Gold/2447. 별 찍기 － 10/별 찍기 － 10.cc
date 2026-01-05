#include<iostream>
using namespace std;

int arr[3000][3000] = { {0,},};

void print_star(int len, int x, int y) {
	if (len==1) {
		arr[x][y] = 1;
		return;
	}
	int new_len = len / 3;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j <  3; j++) {
			if (i==1 && j==1) {
				continue;
			}
			print_star(new_len, x + i * new_len, y + j * new_len);
		}
	}

}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N;
	cin >> N;

	print_star(N, 0, 0);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			char x = arr[i][j] == 1 ? '*' : ' ';
			cout << x;
		}
		cout << "\n";
	}

	return 0;
}
/*
3^7 = 27 * 81 = 2167
재귀적으로 좌상단에 꼭짓점으로부터 3*3 크기의 상자로 호출 되었을때 저 문양을 찍도록 한다.
*/