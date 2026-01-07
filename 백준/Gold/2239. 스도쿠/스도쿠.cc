#include <iostream>
using namespace std;

int arr[9][9];
bool flag = false;

int row_visited[9] = { 0, };
int col_visited[9] = { 0, };
int box_visited[9] = { 0, };

int box_idx(int x, int y) {
    return (x / 3) * 3 + (y / 3);
}

void fill_board(int x, int y) {
    if (flag) return;

    if (x == 9) {   // 끝까지 다 채움
        flag = true;
        return;
    }

    int nx = x, ny = y + 1;
    if (ny == 9) { nx++; ny = 0; }

    if (arr[x][y] != 0) {     // 이미 채워진 칸
        fill_board(nx, ny);
        return;
    }

    for (int i = 1; i <= 9; i++) {
        int b = box_idx(x, y);

        if ((row_visited[x] & (1 << i)) ||
            (col_visited[y] & (1 << i)) ||
            (box_visited[b] & (1 << i)))
            continue;

        row_visited[x] |= (1 << i);
        col_visited[y] |= (1 << i);
        box_visited[b] |= (1 << i);
        arr[x][y] = i;

        fill_board(nx, ny);

        if (flag) return;

        row_visited[x] ^= (1 << i);
        col_visited[y] ^= (1 << i);
        box_visited[b] ^= (1 << i);
        arr[x][y] = 0;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    for (int i = 0; i < 9; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < 9; j++) {
            arr[i][j] = s[j] - '0';
            if (arr[i][j] != 0) {
                int v = arr[i][j];
                row_visited[i] |= (1 << v);
                col_visited[j] |= (1 << v);
                box_visited[box_idx(i, j)] |= (1 << v);
            }
        }
    }

    fill_board(0, 0);

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++)
            cout << arr[i][j];
        cout << "\n";
    }
}
