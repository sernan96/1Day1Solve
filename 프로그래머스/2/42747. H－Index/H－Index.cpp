#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    int n = citations.size();
    sort(citations.begin(), citations.end());
    // 0 1 4 5 6
    //고려할 점 
    //딱맞아 떨어지지 않을 수도 있음
    for(int i = 0; i < n; i++){
        for(int h = citations[i]; h>0; h--){
            if((n-i)>=h){
                answer = max(answer, h);
                break;
            }
        }
    }
    
    return answer;
}