import java.util.*;
class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        ArrayList<String> arrCommand = new ArrayList<>();
        ArrayList<Integer> arrScore = new ArrayList<>();
        char [] x = dartResult.toCharArray();
        StringBuilder slice = new StringBuilder();
        for(int i=0; i<dartResult.length(); i++){
            if(x[i]=='S'||x[i]=='D'||x[i]=='T'){
                if(slice.length()!=0){
                    arrScore.add(Integer.parseInt(slice.toString()));
                    slice.setLength(0);
                }
                if(i+1<dartResult.length()&&(x[i+1]=='#' ||x[i+1]=='*')){
                    arrCommand.add(Character.toString(x[i]) + x[i + 1]);
                    i++;
                }
                else{
                    arrCommand.add(Character.toString(x[i]));
                }
            }
            
            else {
                slice.append(x[i]);
            }
        }
        System.out.println(arrCommand);
        ArrayList<Integer> arrNewscore = new ArrayList<>();
        for(int i=0; i<arrCommand.size(); i++){
            if(arrCommand.get(i).length()==1){
                int roundScore = arrScore.get(i);
                char command = arrCommand.get(i).charAt(0);
                if(command=='S'){
                    arrNewscore.add(roundScore);
                }
                else if(command =='D'){
                    arrNewscore.add(roundScore*roundScore);
                }
                else{
                    arrNewscore.add(roundScore*roundScore*roundScore);
                }
            }
            else{
                int roundScore = arrScore.get(i);
                char command = arrCommand.get(i).charAt(0);
                char award = arrCommand.get(i).charAt(1);
                int baesoo =1;
                if(award=='*'){
                    baesoo=2;
                    if(i!=0){
                        arrNewscore.set(i-1,2*arrNewscore.get(i-1));    
                    }
                }
                else if(award=='#'){
                    baesoo=-1;
                }
                if(command=='S'){
                    arrNewscore.add(roundScore*baesoo);
                }
                else if(command =='D'){
                    arrNewscore.add(roundScore*roundScore*baesoo);
                }
                else if(command =='T'){
                    arrNewscore.add(roundScore*roundScore*roundScore*baesoo);
                }
            }
            
        }
        for(int num :arrNewscore){
            answer+=num;
            System.out.print(num+" ");
        }
        return answer;
    }
}