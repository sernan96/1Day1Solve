#include <iostream>
#include <vector>
using namespace std;

int R, C;
pair<int, int> air_fresher = { 0, 0 };
int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};
int map[51][51] = { {0,}, };

bool can_spread(int x, int y) {
    return x > 0 && y > 0 && x <= R && y <= C && map[x][y] != -1;
}

void spread_dust() {
    vector<vector<int>> sum_arr(R + 1, vector<int>(C + 1, 0));
    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
            if (map[i][j] > 0) {
                for (int dir = 0; dir < 4; dir++) {
                    int mx = i + dx[dir];
                    int my = j + dy[dir];
                    int origin_dust_size = map[i][j];
                    if (can_spread(mx, my)) {
                        sum_arr[mx][my] += origin_dust_size / 5;
                        sum_arr[i][j] -= origin_dust_size / 5;
                    }
                }
            }
        }
    }
    // 반영
    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
            map[i][j] += sum_arr[i][j];
        }
    }
}

void action_air_fresher(int x, int y, bool is_clock) {
    //반시계 방향이면 시계방향으로 앞의 값 불러오기
    if (!is_clock) {
        //올라가야함
        if (y == 1 && x > 1) {
            map[x][y] = map[x - 1][y];
            action_air_fresher(x - 1, y, is_clock);
        }
        //우측으로 가야함
        else if (x == 1 && y < C) {
            map[x][y] = map[x][y + 1];
            action_air_fresher(x, y + 1, is_clock);
        }
        //아래로 가야함
        else if (y == C && x < air_fresher.first) {
            map[x][y] = map[x + 1][y];
            action_air_fresher(x + 1, y, is_clock);
        }
        //좌측으로 가야함 (공기청정기 입구까지 도달)
        else if (x == air_fresher.first && y > 2) {
            map[x][y] = map[x][y - 1];
            action_air_fresher(x , y - 1, is_clock);
        }
    }
    //시계 방향이면 반시계 방향으로 앞의 값 불러와서 저장
    else {
        //아래로 가야함
        if (y == 1 && x < R) {
            map[x][y] = map[x + 1][y];
            action_air_fresher(x + 1, y, is_clock);
        }
        //우측으로 가야함
        else if (x == R && y < C) {
            map[x][y] = map[x][y + 1];
            action_air_fresher(x, y + 1, is_clock);
        }
        //올라가야함
        else if (y == C && x > air_fresher.second) {
            map[x][y] = map[x - 1][y];
            action_air_fresher(x - 1, y, is_clock);
        }
        //좌측으로 가야함 (공기청정기 입구까지 도달)
        else if (x == air_fresher.second && y > 2) {
            map[x][y] = map[x][y - 1];
            action_air_fresher(x , y - 1, is_clock);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T;
    cin >> R >> C >> T;
    bool is_up = true;
    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
            cin >> map[i][j];
            if (map[i][j] == -1) {
                if (is_up) {
                    air_fresher.first = i;
                    is_up = false;
                }
                else {
                    air_fresher.second = i;
                }
            }
        }
    }
    while (T-- != 0) {
        // 미세먼지 확산
        spread_dust();
        
        // 공기청정기 작동
        // 반시계
        action_air_fresher(air_fresher.first - 1, 1, false);
        // 시계
        action_air_fresher(air_fresher.second + 1, 1, true);
        //송풍구 바로 앞은 싱싱한 공기
        map[air_fresher.first][2] = 0;
        map[air_fresher.second][2] = 0;
    }
    int sum = 2;
    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
            sum += map[i][j];
        }
    }
    cout << sum;
    return 0;
}
