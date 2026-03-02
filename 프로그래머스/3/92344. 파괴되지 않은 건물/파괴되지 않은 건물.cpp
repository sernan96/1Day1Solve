#include <string>
#include <vector>
#include <iostream>

using namespace std;
int N, M;
int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    N = board.size();
    M = board[0].size();
    vector<vector<int>> skill_sum(N, vector<int>(M, 0));
    for(vector<int> cur_skill : skill){
        int type = cur_skill[0]==1? -1: 1;
        int degree = cur_skill[5] * type;
        int start_x = cur_skill[1];
        int start_y = cur_skill[2];
        int end_x = cur_skill[3];
        int end_y = cur_skill[4];
        //누적합 시작 위치에 degree 넣어주기
             
        skill_sum[start_x][start_y] += degree;     
        if(end_x + 1 < N){
            skill_sum[end_x + 1][start_y] -= degree;         
        }
        if(end_y + 1 < M){
            skill_sum[start_x][end_y + 1] -= degree;
        }
        if(end_x + 1 < N && end_y + 1 < M){
            skill_sum[end_x + 1][end_y + 1] += degree;         
        }
    }
    //가로
    for(int i=0; i<N; i++){
        for(int j=0; j<M-1; j++){
            skill_sum[i][j+1] += skill_sum[i][j];
        }
    }
    //세로
    for(int j=0; j<M; j++){
        for(int i=0; i<N-1; i++){
            skill_sum[i+1][j] += skill_sum[i][j];
        }
    }
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(board[i][j] + skill_sum[i][j]<=0){
                answer++;
            }
        }
    }
    return N*M - answer;
}
/*
단순 구현으로 전부 직접 해준다면 무조건 시간초과이다.
최악의 경우 100만(N*M) * 25만(skill의 시전 횟수) = 2500억이란 어마무시한 숫자가 나오게 된다.

내가 생각한 방법
skill에 해당하는 연산을 2차원 구간합으로 skill에 해당하는 숫자 전부 갱신해두고
한번에 연산 후 board에 반영해준다.
예를 들어
[[1,1,1,2,2,4],[1,0,0,1,1,2],[2,2,0,2,0,100]]

그렇게 한다면 내가 값을 채워주고 싶은 영역의 시작 위치에 값을 넣고
그에 반대되는 상충값을 영역이 끝나는 바로 다음 영역에 삽입해준다.
예) 
4*4 배열에서
skill_sum 배열이라 했을때(누적합을 진행해줄 배열)
그런데 단순 내가 하던 2차원 배열 누적합 하듯 해주면 시간초과가 발생
최악의 경우 5초가 발생하게 되는데 정확성 테스트에서는 통과하지만 
효율성 테스트에서 시간초과가 발생하게 된다.


*/