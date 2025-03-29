import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String [] hms = play_time.split(":");
        int playTime = Integer.parseInt(hms[0])*3600+Integer.parseInt(hms[1])*60+Integer.parseInt(hms[2]);
     
        hms = adv_time.split(":");
        int advTime = Integer.parseInt(hms[0])*3600+Integer.parseInt(hms[1])*60+Integer.parseInt(hms[2]);
        
        long [] count = new long[playTime+2];
        
        for(String log:logs){
            String [] time = log.split("-");
            StringTokenizer first = new StringTokenizer(time[0],":");
            int startTime = Integer.parseInt(first.nextToken())*3600+Integer.parseInt(first.nextToken())*60+Integer.parseInt(first.nextToken());
            StringTokenizer second = new StringTokenizer(time[1],":");
            int endTime = Integer.parseInt(second.nextToken())*3600+Integer.parseInt(second.nextToken())*60+Integer.parseInt(second.nextToken());
            count[startTime]++;
            count[endTime]--;
        }
        for(int i=1; i<playTime+1; i++){
            count[i]+=count[i-1];
        }
        for(int i=1; i<playTime+1; i++){
            count[i]+=count[i-1];
        }
        long max = 0;
        int maxTime = 0;
        for(int i=0; i<=playTime+1-advTime; i++){
            if(i==0){
                if(max<count[i+advTime-1]){
                    max = count[i+advTime];
                    maxTime = i;
                }
            }   
            else{
                long gap = count[i+advTime-1]-count[i-1];
                if(gap>max){
                    max = gap;
                    maxTime = i;
                }
            }
        }
        String hour = maxTime/3600<10? "0"+String.valueOf(maxTime/3600):String.valueOf(maxTime/3600);
        String minute = maxTime%3600/60<10? "0"+String.valueOf(maxTime%3600/60):String.valueOf(maxTime%3600/60);
        String second = maxTime%60<10? "0"+String.valueOf(maxTime%60):String.valueOf(maxTime%60);
        
        return hour+":"+minute+":"+second;
    }
}
// 누적합으로 초단위로 전부 +1씩 해주고 
// 슬라이딩 윈도우?
// 구간합으로 구하기 -> ㄱㄱ
// 간격은 단지 adv_time
// 계산해보니 최대 초는 359999 -> 배열크기 360000으로 해주기
// 그런데 로그가 30만개임 -> 누적합처리 해주기만 해도 시간초과가 날듯
// 그런데 생각해보니 시작엔 +1
// 끝지점 다음엔 -1을 넣어주면 된다.
// 그럼 한번만 돌아도 충분함
