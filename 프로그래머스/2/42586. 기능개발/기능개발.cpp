#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> q;
    for(int i=0; i<progresses.size(); i++){
        int mod = (100-progresses[i])/speeds[i];
        mod +=(100-progresses[i])%speeds[i]>0? 1:0;
        q.push(mod);
    }
    while(!q.empty()){
        int cur = q.front();
        q.pop();
        int cnt = 1;
        while(!q.empty() && q.front()<=cur){
            q.pop();
            cnt++;
        }
        answer.push_back(cnt);
    }
    return answer;
}