#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int w = brown/2;
    for(; w>0; w--){
        for(int h = w; h>0; h--){
            int remain = brown;
            int clarify_w = w;
            int clarify_h = h;
            int rect_brown = 2*clarify_h+2*clarify_w-4;
            while(rect_brown <= remain && clarify_w >0 && clarify_h>0){
                remain-=rect_brown;
                clarify_w-=2;
                clarify_h-=2;
                rect_brown = 2*clarify_h+2*clarify_w-4;
            }
            if(remain==0 && w*h == brown+yellow){
                return {w, h};
            }
        }
    }
    return answer;
}
// brown의 테두리 너비를 기준으로 가로 세로를 구하고 나머지 yellow로 가득채울 수 있다면 return
/*
2w-2 + 2h-2가 갈색 가장 바깥 너비 
안쪽으로 채워가며 갈색의 개수가 0이되면 
나머지 남은 칸의 개수가 노란색의 개수와 같으면 조건 충족 

*/