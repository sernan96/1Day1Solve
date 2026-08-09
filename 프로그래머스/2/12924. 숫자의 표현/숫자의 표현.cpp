#include <string>
#include <vector>
#include <queue>
#include <iostream>
using namespace std;

int solution(int n) {
    int answer = 0;
    deque<int> dq;
    int sum = 0;
    for(int i=1; i<=n; i++){
        
        dq.push_back(i);
        sum +=i;
        if(sum==n){
            answer++;
        }
        while(!dq.empty() && sum>n){
            int trash = dq.front();
            sum-=trash;
            dq.pop_front();
            if(sum==n){
                answer++;
            }
        }
    }
    return answer;
}