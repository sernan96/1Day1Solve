#include <string>
#include <vector>

using namespace std;

int answer, t, len;
vector<int> num;
void DFS(int now, int sum){
    if(now==len){
        if(sum == t){
            answer++;
        }
        return;
    }
    else{
        DFS(now+1, sum+num[now]);
        DFS(now+1, sum-num[now]);
    }
}
int solution(vector<int> numbers, int target) {
    answer = 0;
    num = numbers;
    len = numbers.size();
    t = target;
    DFS(0, 0);
    return answer;
}
