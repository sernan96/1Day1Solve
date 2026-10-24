#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int n, m1, m2;
vector<vector<int>> graph;


int main() {
    cin >> n >> m1 >> m2;
    graph.assign(n+1, vector<int>());
    vector<int> indegree(n+1, 0);
    indegree[0] = 1e9;
    int a, b;
    for (int i = 0; i < m1; i++) {
        cin >> a >> b;
        indegree[b]++;
        graph[a].push_back(b);
    }

    for (int i = 0; i < m2; i++) {
        cin >> a >> b;
    }

    // Please write your code here.
    queue<int> q;

    for(int i=1; i<=n; i++){
        if(indegree[i]==0){
            q.push(i);
        }
    }
    int cnt = 0;

    while(!q.empty()){
        int cur = q.front();
        q.pop();

        for(int x: graph[cur]){
            indegree[x]--;
            if(indegree[x]==0){
                q.push(x);
            }
        }
        cnt++;
    }
    if(cnt==n){
        cout<<"Yes";
        return 0;
    }
    cout<<"No";
    return 0;
}