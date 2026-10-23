#include <iostream>
#include <vector>
using namespace std;

int n, m;
vector<int> parent;

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

    // Please write your code here.
    for(int i=0; i<m; i++){
        int cmd, a, b;
        cin>>cmd>>a>>b;
        //find먼저하고 커맨드에 따라 추가 동작
        int p_a = find(a);
        int p_b = find(b);
        if(cmd==0 && p_a != p_b){
            union_(a, b);
        }
        else if(cmd==1){
            int answer = p_a != p_b? 0: 1;
            cout<<answer<<endl;
        }
    }
    return 0;
}