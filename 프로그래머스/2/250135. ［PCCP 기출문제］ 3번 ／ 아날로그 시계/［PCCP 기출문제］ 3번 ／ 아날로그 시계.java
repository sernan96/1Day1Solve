import java.util.*;
class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        int startTime = h1*3600+m1*60+s1;
        int endTime = h2*3600+m2*60+s2;
        answer = getCount(endTime) - getCount(startTime);
        if(startTime*59%3600==0||startTime*719%43200==0){
            answer++;
        }
        return answer;
    }
    static int getCount (int time){
        int mCount = time*59/3600;
        int hCount = time*719/43200;
        int subCount = time>=43200? 2: 1;
        return mCount+hCount-subCount;
    }
}