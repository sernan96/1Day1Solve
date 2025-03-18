import java.util.*;
class Solution {
    static int N, result;
    static int [] p, answer, count;
    public int[] solution(int n, int[] info) {
        p = info;
        N = n;
        count = new int[11];
        result = Integer.MIN_VALUE;
        DFS(0);
        return result==Integer.MIN_VALUE?new int[]{-1}:answer;
    }
    static void DFS( int len){
        if(len==N){
            int peach = 0;
            int lion = 0;
            //점수 계산
            for(int i=0; i<=10; i++){
                if(p[i]+count[i]==0){
                    continue;
                }
                if(p[i]>=count[i]){
                    peach+=(10-i);
                }
                else{
                    lion+=(10-i);
                }
            }
            if(lion-peach>0&&result<=lion-peach){
                result = lion-peach;
                answer = count.clone();
            }
            return;
        }
        for(int i=0; i<11; i++){
            if(count[i]>p[i]){
                break;
            }
            count[i]++;
            DFS( len+1);
            count[i]--;
        }
    }
}

//DFS로 화살 10개 배분 0점부터 해줌
//DFS 파라미터로는 int[] count, int len
//마지막에 clone해서 ArrayList<int[]>에 넣기
//저장된 배열을 하나씩 돌면서 어피치랑 비교하고 가장 큰 점수를 맞힌 경우를 저장하고 있다가
//마지막에 return 