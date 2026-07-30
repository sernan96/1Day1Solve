#include <string>
#include <vector>
#include <queue>
#include <set>
#include <algorithm>
//테스트용
#include <iostream>

using namespace std;

void makeNM(int len);
void BFS();

static int answer, N, K, start_point;
vector<vector<int>> graph;
vector<int> order;
set<int> infected;


int solution(int n, int infection, vector<vector<int>> edges, int k) {
    answer = 0;
    N = n;
    K = k;
    start_point = infection;
    order.assign(K+1, 0);
    graph.assign(N+1, vector<int>(N+1, 0));
    for(vector<int> edge: edges){
        int start = edge[0];
        int end = edge[1];
        
        graph[start][end] = edge[2];
        graph[end][start] = edge[2];
    }
    makeNM(0);
    return answer;
}

void makeNM(int len){
    for(int i=1; i<=3; i++){
        infected.clear();
        
        order[len]= i;
        BFS();
        if(len+1 < K){
            makeNM(len+1);
        }
        order[len] = 0;
    }
}

void BFS(){
    //BFS로 각 단계에 맞는 이동경로 선택해서 추가 
    vector<bool> visited(N+1, false);
    queue<vector<int>> q;
    q.push({start_point, 0});
    visited[start_point] = true;
    infected.insert(start_point);
    
    while(!q.empty()){
        vector<int> cur_info = q.front(); //0에는 노드 정보, 1에는 카운트
        q.pop();
        for(int next =1; next<=N; next++){//0에는 노드 정보, 1에는 해당 파이프의 종류
            if(graph[cur_info[0]][next]==0 || next == cur_info[0]){
                continue;
            }
            if(cur_info[1] < K && !visited[next] && (order[cur_info[1]])==graph[cur_info[0]][next]){
                infected.insert(next);
                visited[next] = true;
                q.push({next, cur_info[1]});
            }
            //현재 이동 불가면 그냥 기다리기
            else if(cur_info[1] < K){
                q.push({cur_info[0], cur_info[1]+1});
            }
        }
    }
    answer = max(answer, (int)infected.size());
    cout<<(int)infected.size()<<endl;
}
/*
열고닫는  파이프 번호를 브루트포스로 생성 -> 길이는 1이상 k이하 
문자열 생성마다 BFS 돌고 최대 감염된 배양체의 개수를 전역 answer 변수에 반영
해당 문자열 기준으로 BFS 진행하며 해당 순번에 열리지 않은 파이프는 이동 X

*/