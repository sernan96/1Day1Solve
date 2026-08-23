#include<stack>
#include<string>
using namespace std;

int solution(string s)
{
    stack<char> st;
    for(char x: s){
        if(!st.empty() && st.top() == x){
            st.pop();
        }
        else{
            st.push(x);
        }
    }
    return st.empty()? 1: 0;
}
/*
stack에다 하나씩 쌓아가면서 짝이면 삭제 
아니면 쌓기

*/