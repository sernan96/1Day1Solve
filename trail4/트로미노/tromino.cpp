#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m, result;
int grid[200][200];
int dx [] = {1, 0, -1, 0};
int dy [] = {0, 1, 0, -1};
vector<vector<bool>> visited;
bool can_go(int x, int y){
    return x>=0 && y>=0 && x<n && y<m;
}
void DFS(int x, int y, int len, int sum){

    if(len==3){
        result = max(result, sum);
    }
    else if(len <3){
        for(int i=0; i<4; i++){
            int mx = dx[i] + x;
            int my = dy[i] + y;
            if(can_go(mx, my) && !visited[mx][my]){
                visited[mx][my] = true;
                DFS(mx, my, len+1, sum+grid[mx][my]);
                visited[mx][my] = false;
            }
        }
    }
}

int main() {
    cin >> n >> m;
    result = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> grid[i][j];
        }
    }
    visited.assign(n, vector<bool>(m, false));
    // Please write your code here.
    //각 칸에서 DFS로 3칸 깊이만큼 이동했을때 적힌 수의 합의 최대를 찾는다.
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            visited[i][j] = true;
            DFS(i, j, 1, grid[i][j]);
            visited[i][j] = false;
        }
    }
    cout<<result;
    return 0;
}
