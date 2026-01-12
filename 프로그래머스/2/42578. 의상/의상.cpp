#include <string>
#include <vector>
#include <map>
using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    int N = clothes.size();
    int M = clothes[0].size();
    map<string, int> map;
    for(vector<string> v : clothes){
        if(map[v[1]]<0){
            map[v[1]] = 1;
        }
        else{
            map[v[1]]  = map[v[1]] + 1;
        }
    }
    for(auto it = map.begin(); it!=map.end(); ++it){
        answer*=(it->second+1);
    }
    return answer-1;
}
/*
각 종류별 항목 개수 + 1 씩해서 전부 곱해줌 그리고 모두 안입은 경우 -1해주면 끝
*/