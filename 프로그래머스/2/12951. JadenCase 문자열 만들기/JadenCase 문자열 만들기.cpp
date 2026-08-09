#include <string>
#include <vector>
#include <cctype>

using namespace std;

string solution(string s) {
    string answer = "";
    char pre = ' ';
    for(char x: s){
        char up_x  = toupper(x);
        char down_x  = tolower(x);
        if(pre ==' '){
            answer = answer+up_x;  
        }
        else{
            answer = answer+down_x;
        }
        pre = x;
    }
    return answer;
}