#include <iostream>
#include <vector>
#include <cmath> // abs, round 등 사용 시 필요
#include <iomanip> 

using namespace std;

vector<pair<long long, long long>> points; 

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N;
    cin >> N;

    for (int i = 0; i < N; i++) {
        int x, y;
        cin >> x >> y;
        points.push_back({ x, y });
    }

    double sum1 = 0;
    double sum2 = 0;

    for (int i = 0; i < N; i++) {
        // 현재 점과 다음 점 (마지막 점이면 첫 번째 점과 연결)
        int next = (i + 1) % N; 
        
        sum1 += points[i].first * points[next].second;
        sum2 += points[i].second * points[next].first;
    }

    double area = abs(sum1 - sum2) / 2.0;

    cout << fixed << setprecision(1) << area << "\n";

    return 0;
}