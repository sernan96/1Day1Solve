#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n, m;
vector<vector<int>> graph;

struct cmp{
    const bool operator()(const int & a, const int & b){
        return a>b;
    }
};

int main() {
    cin >> n >> m;
    vector<int> indegree(n+1, 0); 
    graph.assign(n+1, vector<int>());
    indegree[0]=1e9;
    for (int i = 0; i < m; i++) {
        int from, to;
        cin >> from >> to;
        indegree[to]++;
        graph[from].push_back(to);
    }

    // Please write your code here.
    priority_queue<int, vector<int>, cmp> pq;
    for(int i=1; i<=n; i++){
        if(indegree[i]==0){
            pq.push(i);
        }
    }
    while(!pq.empty()){
        int cur = pq.top();
        pq.pop();
        for(int x: graph[cur]){
            indegree[x]--;
            if(indegree[x]==0){
                pq.push(x);
            }
        }
        cout<<cur<<" ";
    }
    return 0;
}