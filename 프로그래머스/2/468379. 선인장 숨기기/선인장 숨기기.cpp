#include <bits/stdc++.h>
using namespace std;

vector<int> solution(int m, int n, int h, int w, vector<vector<int>> drops) {
    int INF = drops.size() + 1;

    vector<vector<int>> a(m, vector<int>(n, INF));

    for (int i = 0; i < (int)drops.size(); i++)
        a[drops[i][0]][drops[i][1]] = i + 1;

    // 가로 w칸의 최솟값
    vector<vector<int>> b(m, vector<int>(n - w + 1));

    for (int i = 0; i < m; i++) {
        deque<int> q;

        for (int j = 0; j < n; j++) {
            while (!q.empty() && a[i][q.back()] >= a[i][j])
                q.pop_back();

            q.push_back(j);

            if (q.front() <= j - w)
                q.pop_front();

            if (j >= w - 1)
                b[i][j - w + 1] = a[i][q.front()];
        }
    }

    // 세로 h칸의 최솟값을 구하면서 정답 탐색
    int best = -1;
    vector<int> answer = {0, 0};

    for (int j = 0; j < n - w + 1; j++) {
        deque<int> q;

        for (int i = 0; i < m; i++) {
            while (!q.empty() && b[q.back()][j] >= b[i][j])
                q.pop_back();

            q.push_back(i);

            if (q.front() <= i - h)
                q.pop_front();

            if (i >= h - 1) {
                int x = i - h + 1;
                int val = b[q.front()][j];

                // 핵심 수정:
                // val이 같으면 행이 작은 좌표,
                // 행도 같으면 열이 작은 좌표 선택
                if (val > best ||
                    (val == best &&
                     (x < answer[0] ||
                      (x == answer[0] && j < answer[1])))) {
                    best = val;
                    answer = {x, j};
                }
            }
        }
    }

    return answer;
}