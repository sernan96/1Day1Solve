import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int [][] arr = new int[want.length][discount.length+1];
        for(int j=1; j<=discount.length; j++){
            for(int i=0; i<want.length; i++){
                if(want[i].equals(discount[j-1])){
                    arr[i][j]++;
                }
            }
        }
        for(int j=1; j<=discount.length; j++){
            for(int i=0; i<want.length; i++){
                arr[i][j]+=arr[i][j-1];
            }
        }
        for(int j=0; j+10<=discount.length; j++){
            boolean flag = true;
            for(int i=0; i<want.length; i++){
                if(arr[i][j+10]-arr[i][j]<number[i]){
                    flag = false;
                    break;
                }   
            }
            if(flag){
                answer++;
            }
        }
        return answer;
    }
}
/*
구간합으로 구하면 될듯
2차원 배열로 열은 날짜 행은 종류
반례 
-> 만약 10일을 다 채우지 않더라도 조건은 충족할 수 있을 수 있다. -> 아니다. number원소의 합은 10이다.

*/