#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int N;
        cin >> N;

        vector<int> pick(N + 1);
        for (int i = 1; i <= N; i++) cin >> pick[i];

        // state: 0=미방문, 1=현재 탐색 경로(진행중), 2=처리완료
        vector<int> state(N + 1, 0);

        // idx[u] : "이번 탐색에서" u가 경로 벡터 order의 몇 번째 인덱스였는지
        vector<int> idx(N + 1, -1);
        // mark[u] : idx[u]가 유효한지 확인하기 위한 라운드 번호(시작점마다 1씩 증가)
        vector<int> mark(N + 1, 0);

        int round = 0;
        int cycleCnt = 0;

        for (int s = 1; s <= N; s++) {
            if (state[s] != 0) continue;

            round++;
            int u = s;
            vector<int> order;
            order.reserve(64);

            // 방문 안 한 동안 계속 따라가기(사실상 한 줄짜리 BFS/경로추적)
            while (state[u] == 0) {
                state[u] = 1;              // 현재 경로에 포함
                mark[u] = round;
                idx[u] = (int)order.size();
                order.push_back(u);
                u = pick[u];
            }

            // 사이클 판정:
            // state[u]==1 이면서, mark[u]==round 이면 "이번 경로 안에서 다시 만난 노드" → 사이클
            if (state[u] == 1 && mark[u] == round) {
                cycleCnt += (int)order.size() - idx[u];
            }

            // 이번에 따라간 경로 노드들 처리 완료로 마감
            for (int x : order) state[x] = 2;
        }

        cout << (N - cycleCnt) << "\n";
    }

    return 0;
}