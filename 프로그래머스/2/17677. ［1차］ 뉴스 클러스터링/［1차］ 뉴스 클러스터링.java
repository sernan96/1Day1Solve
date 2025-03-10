import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        ArrayList<String> A= new ArrayList<>();
        ArrayList<String> B= new ArrayList<>();
        ArrayList<String> sum= new ArrayList<>();
        for(int i=0; i<str1.length()-1; i++){
            String temp = str1.substring(i, i+2);
            
            if(isAlpha(temp.charAt(0))&&isAlpha(temp.charAt(1))){
                temp = temp.toUpperCase();
                A.add(temp);
                sum.add(temp);
                System.out.println("A: "+temp);
            }
        }
        for(int i=0; i<str2.length()-1; i++){
            String temp = str2.substring(i, i+2);
            if(isAlpha(temp.charAt(0))&&isAlpha(temp.charAt(1))){
                temp = temp.toUpperCase();
                B.add(temp);
                sum.add(temp);
                System.out.println("B: "+temp);
            }
        }
        int same = 0;
        for(String x: A){
            if(B.contains(x)){
                B.remove(x);
                same++;
            }
        }
        System.out.println(same+", "+sum);
        float answer = (float)same/(float)(sum.size()-same)*(float)65536;
        return A.size()+B.size() ==0? 65536: (int)answer;
    }
    static boolean isAlpha(char x){
        return (x>='A'&&x<='Z')||(x>='a'&&x<='z');
    }
}
//A==65 Z==90 a==97 z==122
// 집합 A, B를 전부 HashSet으로 관리해줌
// 합집합 먼저 구하는데 합집합은 String HashSet에 그냥 넣기
// 그다음 A를 기준으로 잡는다면 A를 순회하며 B에 존재하는지 확인
// 만약 존재한다면 B에서 삭제 
