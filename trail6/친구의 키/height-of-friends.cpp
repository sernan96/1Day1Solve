#include <iostream>
#include <vector>
#include <stack>
using namespace std;

int n, m;
int a[100000], b[100000];
vector<vector<int>> graph;
vector<bool> visited;
stack<int> st;
void DFS(int cur){
    for(int x: graph[cur]){
        if(!visited[x]){
            visited[x] = true;
            DFS(x);
        }
    }
    
    st.push(cur);
}

int main() {
    cin >> n >> m;
    graph.assign(n+1, vector<int>());
    visited.assign(n+1, false);
    for (int i = 0; i < m; i++) {
        cin >> a[i] >> b[i];
        graph[a[i]].push_back(b[i]);
    }

    // Please write your code here.
    for(int i=1; i<=n; i++){
        if(!visited[i]){
            visited[i] = true;
            DFS(i);
        }
    }

    while(!st.empty()){
        cout<<st.top()<<" ";
        st.pop();
    }
    return 0;
}