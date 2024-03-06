import java.util.*;
class Solution {
    static int [][]hardness;
    static int []gock;
    public int solution(int[] picks, String[] minerals) {
        int answer = 0, k =minerals.length/5;
        gock = picks;
        int gocks = gock[0]+gock[1]+gock[2];
        if(gocks==0){
            return answer;
        }
        if(minerals.length%5>0){
            k++;//5개 이하의 조가 편성되었을때
        }
        hardness = new int[k][4];
        int index=0;
        int range=Math.min(minerals.length,gocks*5);
        for(int i =0; i<range; i++){
            score(minerals[i], index);
            if((i+1)%5==0){
              index++;
            }
        }
        Arrays.sort(hardness, Comparator.comparingInt(o1->o1[3]));//돌곡 피로도 오름차순
        index=0;
        for(int i = k-1; i>=0; i--){
            answer+=hardness[i][whichpick()];
        }
        return answer;
    }
    static int whichpick(){
        if(gock[0]>0){
            //다이아 곡 있을때
            gock[0]--;
            return 1;//1열이 다이아
        }
        else if(gock[1]>0){
            gock[1]--;
            return 2;
        }
        else{
            gock[2]--;
            return 3;
        }
    }
    static void score(String a, int index){
        if(a.equals("diamond")){
            // 다 -> 다곡
            hardness[index][1]+=1;
            // 다 -> 철곡
            hardness[index][2]+=5;
            // 다 -> 돌곡
            hardness[index][3]+=25;
        }
        else if(a.equals("iron")){
            
            // 철 -> 다곡
            hardness[index][1]+=1;
            // 철 -> 철곡
            hardness[index][2]+=1;
            // 철 -> 돌곡
            hardness[index][3]+=5;
        }
        else{
            // 돌 -> 다곡
            hardness[index][1]++;
            // 돌 -> 철곡
            hardness[index][2]++;
            // 돌 -> 돌곡
            hardness[index][3]++;
        }
    }
}