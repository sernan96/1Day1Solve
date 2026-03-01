#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<vector<int>> beginning, vector<vector<int>> target) {

    int n = beginning.size();        // 행 개수
    int m = beginning[0].size();     // 열 개수

    // diff[i][j] = 해당 칸을 맞추기 위해
    // "행토글 XOR 열토글" 값이 되어야 하는 값
    // (beginning과 target이 다르면 1, 같으면 0)
    vector<vector<int>> diff(n, vector<int>(m));
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            diff[i][j] = beginning[i][j] ^ target[i][j];

    const int INF = 1e9;
    int answer = INF;

    // c[0] (첫 번째 열을 뒤집을지 말지)는
    // 0 또는 1 두 경우만 존재
    for (int firstCol = 0; firstCol <= 1; firstCol++) {

        vector<int> r(n);   // 각 행 뒤집기 여부
        vector<int> c(m);   // 각 열 뒤집기 여부

        c[0] = firstCol;
        for (int i = 0; i < n; i++) {
            r[i] = diff[i][0] ^ c[0];
        }
        for (int j = 0; j < m; j++) {
            c[j] = diff[0][j] ^ r[0];
        }
        bool possible = true;
        for (int i = 0; i < n && possible; i++) {
            for (int j = 0; j < m; j++) {
                if ((r[i] ^ c[j]) != diff[i][j]) {
                    possible = false;
                    break;
                }
            }
        }
        if (possible) {
            int flipCount = 0;

            for (int i = 0; i < n; i++)
                flipCount += r[i];

            for (int j = 0; j < m; j++)
                flipCount += c[j];

            answer = min(answer, flipCount);
        }
    }

    return (answer == INF) ? -1 : answer;
}