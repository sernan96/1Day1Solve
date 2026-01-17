#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N, M;
    cin >> N >> M;
    //B앞에 A 있어야함 큐에 넣을건 
    vector<vector<int>>graph(N + 1, vector<int>());
    vector < int > front(N + 1, 0);
    queue<int> q;
    while (M-- != 0) {
        int A, B;
        cin >> A >> B;
        //A보다 뒤에 있어야하는 노드들을 그래프로 연결
        graph[A].push_back(B);
        //B보다 앞에 있어야 하는 노드의 개수
        front[B]++;
    }
    for (int i = 1; i <= N; i++) {
        if (front[i] == 0) {
            q.push(i);
        }
    }
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        cout << cur<<" ";
        for (int x: graph[cur]) {
            front[x]--;
            if (front[x] == 0) {
                q.push(x);
            }
        }
        
    }
    return 0;
}