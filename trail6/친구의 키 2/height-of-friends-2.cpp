#include <iostream>
#include <string>
#include <vector>
using namespace std;

int n, m;
bool flag;
vector<vector<int>> graph;
vector<int> visited;

void DFS(int cur){
    for(int x: graph[cur]){
        if(visited[x]==0){
            visited[x] = 1;
            DFS(x);
        }
        else if(visited[x]==1){
            flag = true;
            return;
        }
    }
    visited[cur] = 2;
}

int main() {
    cin >> n >> m;

    flag = false;
    graph.assign(n+1, vector<int>());
    visited.assign(n+1, false);
    for (int i = 0; i < m; i++) {
        int before, after;
        cin>>before>>after;
        graph[before].push_back(after);
    }



    for(int i=1; i<=n; i++){
        if(visited[i]==2) continue;
        visited[i] = 1;
        DFS(i);
    }

    // Please write your code here.
    string s = flag?"Inconsistent":"Consistent";
    cout<<s;
    return 0;
}