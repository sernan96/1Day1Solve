#include <string>
#include <vector>
#include <format>

using namespace std;

vector<int> solution(string s) {
    int cnt_run = 0;
    int cnt_zero = 0;
    while(s!="1"){
        cnt_run++;
        int cnt_one = 0;
        string new_s ="";
        //0제거
        for(int i =0; i<s.length(); i++){
            if(s[i]=='0'){
                cnt_zero++;
            }
            else{
                cnt_one++;
            }
        }
        //값 계산
        s = format("{:b}", cnt_one);
        
    }
    return {cnt_run, cnt_zero};
}