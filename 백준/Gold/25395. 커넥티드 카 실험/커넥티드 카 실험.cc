#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Car {
public:
    int num;
    long long x, h;

    Car(int num = 0, long long x = 0, long long h = 0) {
        this->num = num;
        this->x = x;
        this->h = h;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, S;
    cin >> N >> S;

    vector<Car> cars(N + 1);

    for (int i = 1; i <= N; i++) {
        cars[i].num = i;
        cin >> cars[i].x;
    }
    for (int i = 1; i <= N; i++) {
        cin >> cars[i].h;
    }

    sort(cars.begin() + 1, cars.end(), [](const Car& a, const Car& b) {
        return a.x < b.x;
        });

    // 정렬 후 시작 차량 S의 위치 찾기
    int start = -1;
    for (int i = 1; i <= N; i++) {
        if (cars[i].num == S) {
            start = i;
            break;
        }
    }

    long long L = cars[start].x - cars[start].h;
    long long R = cars[start].x + cars[start].h;

    int left = start, right = start;
    bool changed = true;

    while (changed) {
        changed = false;

        // 왼쪽 확장
        while (left > 1 && cars[left - 1].x >= L) {
            left--;
            L = min(L, cars[left].x - cars[left].h);
            R = max(R, cars[left].x + cars[left].h);
            changed = true;
        }

        // 오른쪽 확장
        while (right < N && cars[right + 1].x <= R) {
            right++;
            L = min(L, cars[right].x - cars[right].h);
            R = max(R, cars[right].x + cars[right].h);
            changed = true;
        }
    }

    vector<int> ans;
    for (int i = left; i <= right; i++) {
        ans.push_back(cars[i].num);
    }

    sort(ans.begin(), ans.end());

    for (int x : ans) {
        cout << x << ' ';
    }

    return 0;
}