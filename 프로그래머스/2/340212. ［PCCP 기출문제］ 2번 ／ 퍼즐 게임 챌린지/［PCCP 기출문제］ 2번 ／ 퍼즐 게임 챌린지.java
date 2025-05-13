import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int n = diffs.length;
        if(n==1){
            return 1;
        }
        int left = 1;
        int right = 100000;
        int middle = (left+right)/2;
        
        while(left<=middle){
            boolean flag = false;
            long time = times[0];// 어차피 처음 퍼즐은 이 반복문에 들어온 이상 무조건 풀 수 있는 난이도임
            for(int i=1; i<n; i++){
                if(diffs[i]>middle){// 숙련도가 부족한 경우 
                    time+= (diffs[i]-middle)*(times[i] + times[i-1])+times[i];
                }
                else{
                    time+= times[i];
                }
                if(time>limit){
                    left = middle+1;
                    middle = (left+right)/2;
                    flag = true;
                    break;
                }
            }
            
            if(!flag){
                answer= middle;
                right = middle-1;
                middle = (left+right)/2;
            }
        }
        return answer==0? 1: answer;
    }
}
/*
수가 많기 때문에 완전탐색으로는 불가능하다고 판단 
일단 들어오느 diffs의 최소 최대값을 기준으로 이분탐색 시행
이분탐색으로 각 숙련도를 정하고
숙련도에 따라 퍼즐을 해결할 수 있는지 검사한다.
검사했을때 가능하다 -> left로
검사했을때 시간이 초과다 -> right로
그래서 마지막에 나온 숙련도를 return해줌
*/