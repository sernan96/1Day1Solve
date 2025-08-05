#include<string>
#include <iostream>
#include <stack>

using namespace std;

bool solution(string s)
{
    bool answer = true;
    stack<int> st;
    for(int i=0; i<s.length(); i++){
        char x = s[i];
        if(x=='('){
            st.push(x);
        }
        if(x==')'){
            if(st.empty()||st.top()==')'){
                return false;
            }
            else if(st.top()=='('){
                st.pop();    
            }
        }
    }
    if(!st.empty()){
        return false;
    }
    return answer;
}