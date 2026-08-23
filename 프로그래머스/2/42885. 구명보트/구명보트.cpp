#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
    int answer = 0;
    sort(people.begin(), people.end(), greater<>());
    vector<bool> visited(people.size(), false);
    int right = people.size()-1;
    for(int left = 0; left<people.size(); left++){
        if(visited[left]){
            continue;
        }
        visited[left] = true;//무조건 태움
        answer++;
        //같이 탈 사람 찾기
        while(left<right){
            if(!visited[right] && limit-people[left] >=people[right]){
                visited[right] = true;
                right--;
                break;
            }
            else if(!visited[right] && limit-people[left] < people[right]){
                break;
            }
            else{
                right--;
            }
        }
    }
    return answer;
}
//최대 2명이니까 무겁+가볍 쌍으로 보내는게 최고