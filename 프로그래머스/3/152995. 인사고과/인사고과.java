import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int [] wanho = {scores[0][0],scores[0][1]};
        Arrays.sort(scores, (o1, o2)->{
            if(o1[0]==o2[0]){
                return o1[1]-o2[1];
            }
            return o2[0]-o1[0];
        });
        int maxFriend = scores[0][1];
        for(int i=1; i<scores.length; i++){
            if(scores[i][1]<maxFriend){
                if(wanho[0]==scores[i][0]&&wanho[1]==scores[i][1]){
                    return -1;
                }
                scores[i][0] = -1;
                scores[i][1] = -1;
            }
            else {
                maxFriend = scores[i][1];
            }
        }
        Arrays.sort(scores, (o1, o2)->{
            return (o2[0]+o2[1])-(o1[0]+o1[1]);
        });
        for(int [] x: scores){
            //System.out.println(x[0]+", "+x[1]);
            if(wanho[0]+wanho[1]==x[0]+x[1]){
                break;
            }
            answer++;
        }
        return answer;
    }
}
