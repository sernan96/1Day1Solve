#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int n, m;
int INF_MAX = 1e9;
vector<vector<vector<int>>> graph;
struct cmp{
    bool operator()(const vector<int> &a, const vector<int> &b){
        if(a[1]==b[1]){
            return a[0]>b[0];
        }
        return a[1]>b[1];
    }
};  

int main() {
    cin >> n >> m;
    graph.assign(n+1, vector<vector<int>>());
    for (int i = 0; i < m; i++) {
        int start, end, cost;
        cin>>start>>end>>cost;
        graph[start].push_back({end, cost});
        graph[end].push_back({start, cost});
    }

    // Please write your code here.
    priority_queue<vector<int>, vector<vector<int>>, cmp> pq;
    vector<bool> visited(n+1, false);
    vector<int> dist(n+1, INF_MAX);
    pq.push({1, 0});
    dist[1] = 0;
    int cnt = 0;
    long long total = 0;
    while(!pq.empty()){
        vector<int> cur = pq.top();
        pq.pop();
        if(visited[cur[0]]){
            continue;
        }   
        
        visited[cur[0]]=true;
        cnt++;
        total += cur[1];
        if(cnt==n){
            cout<<total;
            return 0;
        }
        for(vector<int> edge: graph[cur[0]]){
            if(!visited[edge[0]]){
                dist[edge[0]] = edge[1];
                pq.push({edge[0], edge[1]});
            }
        }
    }
    return 0;
}