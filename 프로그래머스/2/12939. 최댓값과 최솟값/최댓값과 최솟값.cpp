#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string s) {
    string answer = "";
    int max_val;
    int min_val;
    string num = "";
    bool first = true;
    for(char x: s){
        if(x!=' '){
            num = num+x;
        }
        else if(first){
            first = false;
            min_val = stoi(num);
            max_val = stoi(num);
            
            num = "";
        }
        else {
            max_val = max(max_val, stoi(num));
            min_val = min(min_val, stoi(num));
            num = "";
        }
    }
    max_val = max(max_val, stoi(num));
    min_val = min(min_val, stoi(num));
    return to_string(min_val)+" "+to_string(max_val);
}