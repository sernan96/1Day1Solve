#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int N;
    cin >> N;

    vector<int> set_u(N, 0);
    for (int i = 0; i < N; i++) {
        cin >> set_u[i];
    }
    sort(set_u.begin(), set_u.end());
    
    int answer = 0;
    for (int k = N - 1; k >= 0; k--) {
        for (int x = 0; x <= k; x++) {
            int y = x;
            int z = k;
            while (y <= z) {
                int sum = set_u[x] + set_u[y] + set_u[z];
                if (sum == set_u[k]) {
                    cout << sum;
                    return 0;
                }
                else if (sum < set_u[k]) {
                    y++;
                }
                else {
                    z--;
                }
            }
        }
    }
    cout << set_u[answer];
    return 0;
}