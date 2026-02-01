#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
vector<vector<int>> graph;
vector<int> dp;

int get_time(int cur) {
    //이미 방문했던 노드라면 바로 return
    if (dp[cur] != -1) {
        //cout<<"재방문: "<< cur<< endl;
        return dp[cur];
    }
    //방문해야하고 만약 리프노드라면 return 1
    if (graph[cur].size()==0) {
        dp[cur] = 1;
        return 1;
    }

    vector<int> children_time;
    for (int x: graph[cur]) {
        int x_time = get_time(x);
        children_time.push_back(x_time);
    }
    sort(children_time.begin(), children_time.end(), greater<>());
    
    int maximum = -1;
    for (int i = 0; i < children_time.size(); i++) {
        maximum = max(maximum, children_time[i] + i);
    }
    dp[cur] = maximum + 1;
    return dp[cur];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N;
    graph.assign(N, vector<int>());
    dp.assign(N, -1);
    for (int i = 0; i < N; i++) {
        int parent;
        cin >> parent;
        if (i != 0) {
            graph[parent].push_back(i);
        }
    }
    cout << get_time(0)-1;
    return 0;
}