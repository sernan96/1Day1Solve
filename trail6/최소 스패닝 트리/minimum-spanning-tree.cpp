#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m;
vector<int> parent;
vector<vector<int>> edge;
struct cmp{
    const bool operator()(vector<int> &a, vector<int> &b){
        return a[2]<b[2];
    }
};

int find(int x){
    if(x==parent[x]){
        return x;
    }
    return parent[x] = find(parent[x]);
}

void union_(int a, int b){
    int p_a = find(a);
    int p_b = find(b);
    if(p_a<p_b){
        parent[p_b] = p_a;
    }
    else{
        parent[p_a] = p_b;
    }
}

int main() {
    cin >> n >> m;
    parent.assign(n+1, 0);
    for(int i=1; i<=n; i++){
        parent[i] = i;
    }
    for (int i = 0; i < m; i++) {
        int start, end, weight;
        cin >> start>>end>>weight;
        edge.push_back({start, end, weight});
    }
    sort(edge.begin(), edge.end(), cmp());

    // Please write your code here.
    int answer = 0;
    for(vector<int> e: edge){
        if(find(e[0])!=find(e[1])){
            union_(e[0], e[1]);
            answer += e[2];
        }
    }
    cout<<answer;
    return 0;
}