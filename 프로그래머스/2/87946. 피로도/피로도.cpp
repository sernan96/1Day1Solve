#include <string>
#include <algorithm>
#include <vector>

using namespace std;

//정렬 기준 
struct {
    const bool operator()(const vector<int> & v1, const vector<int> & v2){
        if(v1[0]<v2[0]){
            return v1[0] < v2[0];
        }
        return v1[1] < v2[1];
    }
}cmp;

//DFS 선언
void DFS(int depth, int tired, int visited);

//전역 변수 선언
vector<vector<int>> list;
int answer = 0;

int solution(int k, vector<vector<int>> dungeons) {
    sort(dungeons.begin(), dungeons.end(), cmp);
    list = dungeons;       
    DFS(0, k, 0);
    return answer;
}

void DFS(int depth, int tired, int visited){
    answer = max(answer, depth);
    for(int i=0; i<list.size(); i++){
        if(list[i][0] > tired){
            continue;
        }
        //방문한 곳이라면 continue
        if((visited & (1<<i))>0){
            continue;
        }
        //첫방문
        DFS(depth +1, tired - list[i][1], visited | (1<<i));
        
    }
}
/*
BFS로 해도 되고
DFS로 해도 됨
근데 DFS가 더 편할듯
파라미터는 depth(던전 수), tired(피로도), visited[](방문한 던전)
최소 필요 피로도 오름차순으로 정렬하고 진행하면 진입 불가능한 분기에 대한 탐색을 줄일 수 있음

*/