#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M, L;
vector<int> arr;

bool can_make(int mid) {
    int cnt = 0;
    for (int i = 1; i < arr.size(); i++) {
        int gap = arr[i] - arr[i - 1];
        cnt += gap / mid;
        if (gap % mid == 0) {
            cnt--;
        }
    }
    return cnt > M;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    cin >> N >> M >> L;
    arr.push_back(0);
    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        arr.push_back(x);
    }
    arr.push_back(L);
    sort(arr.begin(), arr.end());
    int answer = 0;
    int left = 1;
    int right = L;
    int mid = (left + right) / 2;
    while (left <= right) {
        mid = (left + right) / 2;
        if (!can_make(mid)) {
            answer = mid;
            right = mid - 1;
        }
        else {
            left = mid + 1;
        }
    }
    cout << answer;
    return 0;
}
