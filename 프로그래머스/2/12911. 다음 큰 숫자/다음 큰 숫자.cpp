#include <string>
#include <vector>
#include <format>

using namespace std;

int solution(int n) {
    int answer = 0;
    string bin_n = format("{:b}", n);
    int cnt_one = 0;
    for(char x: bin_n){
        if(x=='1'){
            cnt_one++;
        }
    }
    for(int i = n+1; ; i++){
        string bin_i = format("{:b}", i);
        int cnt_one_big = 0;
        for(char x: bin_i){
            if(x=='1'){
                cnt_one_big++;
            }
        }
        if(cnt_one_big==cnt_one){
            return i;
        }
    }
    return answer;
}
//2진수로 변환하고 
//기존 자연수랑 1의 개수가 같은지 check하고 같으면 return