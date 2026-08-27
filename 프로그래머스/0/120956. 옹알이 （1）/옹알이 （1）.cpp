#include <string>
#include <vector>
#include <set>

using namespace std;
string baby[] ={"aya", "ye", "woo", "ma"};
set<string> babblings;

void make_bab(int index, string s, int visited){
    if(s!=""){
        babblings.insert(s);
    }
    for(int i=0; i<4; i++){
        if((visited&(1<<i))==0){        
            make_bab(i, s+baby[i], visited|(1<<i));   
        }
    }
}
int solution(vector<string> babbling) {
    int answer = 0;
    babblings.clear();
    
    make_bab(0, "", 0);
    for(string x: babbling){
        if(babblings.contains(x)){
            answer++;
        }
    }
    return answer;
}

// 각 문자열에서 한번씩만 등장이 가능하다.
// -> 그냥 브루트포스로 조합해서 set에 넣어두고 존재하는지 찾기 