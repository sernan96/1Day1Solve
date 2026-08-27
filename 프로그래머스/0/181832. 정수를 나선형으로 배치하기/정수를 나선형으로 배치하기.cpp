#include <string>
#include <vector>

using namespace std;


vector<vector<int>> answer;
int N;
int dx [] ={0, 1, 0, -1};
int dy [] ={1, 0, -1, 0};

void DFS(int x, int y, int num, int dir){
    answer[x][y] = num;
    int mx = x + dx[dir];
    int my = y + dy[dir];
    
    int cd = (dir+1)%4;
    int nx = x+dx[cd];
    int ny = y+dy[cd];
    if(mx>=0 && mx<N && my>=0 && my<N && answer[mx][my]==0){
        DFS(mx, my, num+1, dir);   
        return;
    }
    else if(nx>=0 && nx<N && ny>=0 && ny<N && answer[nx][ny]==0){
        DFS(nx, ny, num+1, cd);
    }
}
vector<vector<int>> solution(int n) {
    answer.assign(n, vector<int>(n, 0));
    N = n;
    DFS(0, 0, 1, 0);
    
    return answer;
}
