#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>

using namespace std;

int n, m;
vector<vector<vector<int>>> graph;
vector<int> dist;

struct cmp{
    const bool operator()(vector<int> &a, vector<int> &b){
        if(a[1]==b[1]){
            return a[0] >b[0];
        }
        return a[1]>b[1];
    }
};

int main() {
    cin >> n >> m;
    graph.assign(n+1, vector<vector<int>>());
    dist.assign(n+1, 0x7f7f7f7f);
    for (int i = 0; i < m; i++) {
        int start, end, weight;
        cin >> start >> end >> weight;
        graph[start].push_back({end, weight});
    }

    // Please write your code here.
    priority_queue<vector<int>, vector<vector<int>>, cmp> q;
    //{다음 목적지, weight}
    q.push({1, 0});
    dist[1] = 0;
    while(!q.empty()){
        vector<int> cur = q.top();
        q.pop();
        int cur_node = cur[0];
        int cost = cur[1];
        for(vector<int> next: graph[cur_node]){
            if(dist[next[0]]>cost+next[1]){
                q.push({next[0], cost+next[1]});
                dist[next[0]] = cost+next[1];
            }
        }
    }
    for(int i=2; i<=n; i++){
        if(dist[i]==0x7f7f7f7f){
            cout<<-1<<endl;
        }
        else{
            cout<<dist[i]<<endl;
        }
    }
    return 0;
}
