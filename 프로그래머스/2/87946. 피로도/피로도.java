import java.util.*;

class Solution {
    static int N;
    static int [][] info;
    static List<String> combi;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        combi = new ArrayList<>();
        N = dungeons.length;
        info = dungeons;
        makeNM("");
        for(String order: combi){
            answer = Math.max(answer, getTired(order, k));
        }
        return answer;
    }
    static void makeNM(String str){
        if(str.length()==N){
            combi.add(str);
            //System.out.println(str);
            return;
        }
        for(int i=0; i<N; i++){
            char x = (char)(i+'0');
            if(str.indexOf(x)<0){
                makeNM(str+x);
            }
        }
    }
    static int getTired(String order, int k){
        int result = 0;
        for(char x: order.toCharArray()){
            int index = x-'0';
            if(info[index][0]<=k){
                k-=info[index][1];
                result++;
            }
        }
        return result;
    }
}
/*
던전의 개수는 최대 8개이다. 8!의 경우의 수 
인덱스 조합을 만들어서 해당 조합 별 유저가 탐험할 수 있는 최대 던전 수 저장하고 return해주면 끝

*/