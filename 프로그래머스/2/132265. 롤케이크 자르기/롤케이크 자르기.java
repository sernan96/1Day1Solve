import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> right = new HashMap<>();
        HashSet<Integer> left = new HashSet<>();
        for(int top: topping){
            if(right.containsKey(top)){
                right.put(top, right.get(top)+1);
            }
            else{
                right.put(top, 1);
            }
        }
        for(int top: topping){
            if(left.size()==right.size()){
                answer++;
            }
            left.add(top);
            if(right.get(top)==1){
                right.remove(top);
            }
            else{
                right.put(top, right.get(top)-1);
            }
        }
        
        return answer;
    }
    
}
/*
HashMap으로 종류와 개수 저장
하나씩 순회함
잘랐을때 기준 왼쪽 => HashSet
오른쪽 => HashMap
종류 비교는 size로
자르는 지점을 순회하면서 우칙 HashMap이 점점 작아짐
HashMap에서 뺀거는 HashSet에 저장
그렇게 카운트를 해줌
*/