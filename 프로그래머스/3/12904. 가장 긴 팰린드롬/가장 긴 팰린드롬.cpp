#include <iostream>
#include <string>
using namespace std;
int solution(string s)
{
    int answer=1;
    int n = s.size();
    char pre =s[0];
    for(int center=1; center<n; center++){
        int len_hol = 1;
        int len_zzak = 0;
        for(int gap = 1; center-gap>=0 && center+gap < n; gap++){
            if(s[center+gap] == s[center-gap]){
                len_hol+=2;
                answer = max(answer, len_hol);
            }
            else{
                break;
            }
        }
        for(int gap = 1; center-gap>=0 && center+gap-1 < n; gap++){
            if(s[center+gap-1] == s[center-gap]){
                len_zzak+=2;
                answer = max(answer, len_zzak);
            }
            else{
                break;
            }
        }
    }
    return answer;
}
/*
만약 다같은 알파벳이라면 개수 상관없이 개수 세줘야함
*/