#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;

int n, answer;
int A[11][11];
vector<int> route;
vector<vector<int>> dp;
void TPS(int cost, int visited){
    //다 돌았을때
    int pre = route[route.size()-1];
    if(visited==(1<<(n+1))-2 && A[pre][1]!=0){
        answer = min(answer, cost+A[pre][1]);
        return;
    }
    for(int i=2; i<=n; i++){
        int visit = visited |(1<<i);
        if(A[pre][i]!=0 && (visited&(1<<i))==0 && dp[i][visit]> cost + A[pre][i]){
            dp[i][visit] = cost + A[pre][i];
            route.push_back(i);
            TPS(cost+A[pre][i], visit);
            route.pop_back();
        }
    }
}

int main() {
    cin >> n;
    dp.assign(n+1, vector<int>(pow(2, n+1), 0x7f7f7f7f));

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> A[i][j];
        }
    }
    answer = 0x7f7f7f7f;

    // Please write your code here.
    route.push_back(1);
    dp[1][1<<1] = 0;
    TPS(0, 1<<1);
    cout<<answer;

    return 0;
}
