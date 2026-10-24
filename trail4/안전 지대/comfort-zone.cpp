#include <iostream>
#include <vector>
using namespace std;

int N, M;
int grid[50][50];
vector<vector<bool>> visited;
int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};

bool can_go(int x, int y){
    return x<N && y<M && x>=0 && y>=0;
}

void DFS(int x, int y, int k){
    for(int dir = 0; dir<4; dir++){
        int mx = x + dx[dir];
        int my = y + dy[dir];
        if(can_go(mx, my) && !visited[mx][my] && grid[mx][my]>k){
            visited[mx][my] = true;
            DFS(mx, my, k);
        }
    }
}

int main() {
    cin >> N >> M;
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> grid[i][j];
        }
    }
    int answer [2] ={0, -1};//{K, 최대 영역 수}
    // Please write your code here.
    for(int k=1; k<=100; k++){
        visited.assign(N, vector<bool>(M, false));
        int area = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j] || grid[i][j]<=k){
                    continue;
                }        
                visited[i][j] = true;
                area++;
                
                DFS(i, j, k);
                if(answer[1]<area){
                    answer[0] = k;
                    answer[1] = area;
                }
            }
        }

    }
    if(answer[1]==-1){
        cout<<1<<" "<<0;
        return 0;
    }
    cout<<answer[0]<<" "<<answer[1];
    return 0;
}
/*
격자마다 돌면서 DFS로 주변 안전지대 탐색후 방문처리 및 안전영역 개수 세어주기
결과쌍 계속해서 비교하다가 마지막에 출력
만약 안전영역 수가 0이되면 멈추기
*/
