#include <string>
#include <vector>
#include <set>
#include<iostream>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    int end = 1;
    vector<int> answer;
    bool flag = false;
    set<string> dic;
    if(words[0].length()==1){
        return answer;
    }
    dic.insert(words[0]);
    for(; end<words.size(); end++){
        if(words[end].length()==1 || dic.contains(words[end])|| words[end-1][words[end-1].length()-1]!=words[end][0]){
            cout<<end<<"번째: "<<words[end]<<endl;
            flag = true;
            break;
        }
        dic.insert(words[end]);
    }
    if(!flag){
        answer.push_back(0);
        answer.push_back(0);
    }
    else{
        answer.push_back((end)%n+1);
        answer.push_back(end/n+1);
    }
    return answer;
}