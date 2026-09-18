#include <math.h>

using namespace std;

int solution(int n, int a, int b)
{
    int answer = 1;
    while((a + 1) / 2 != (b + 1) / 2){
        if(a%2!=0){
            //a가 홀수
            a = a / 2 + 1;
        }
        else{
            a/=2;
        }
        if(b%2!=0){
            //b가 홀수
            b = b / 2 + 1;
        }
        else{
            b/=2;
        }
        answer++;
    }
    return answer;
}