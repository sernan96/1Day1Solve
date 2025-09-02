import java.util.*;

class Solution {
    public int solution(int[] order) {
        int box = 1;
        int index = 0;
        ArrayDeque<Integer> sub = new ArrayDeque<>();
        //box 넘버를 기준으로 반복
        while(true){
            //바로 상차가 가능한 경우
            if(index<order.length&&order[index]==box){
                index++;
                box++;
            }
            // sub 벨트에서 바로 꺼내기 가능
            else if(!sub.isEmpty()&&order[index]==sub.peekLast()){
                sub.pollLast();
                index++;
            }
            else if(box<=order.length){
                sub.addLast(box);
                box++;
            }
            else{
                break;
            }
            
            
        }
        return index;
    }
}
/*보조 컨베이어벨트는 스택이고
일단 택배기사님의 순서에 안맞으면 stack에 저장
만약에 적합한 것이 나오면 상차
만약에 아니다.
근데 아닌 경우가 2가지

1. 스택에 있는 경우 -> 이경우엔 해당 택배 상자가 top이 아니면 종료
-> top에 원하는 상자있으면 뽑아서 쓰기

2. 아직 안나온 경우 -> 나올때까지 스택에 저장 -> 만약 이제 없으면 끝

*/