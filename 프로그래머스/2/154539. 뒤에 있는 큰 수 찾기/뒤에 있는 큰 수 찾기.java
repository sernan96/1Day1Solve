import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i=numbers.length-1; i>=0; i--){
            //stack이 비어있을 경우
            if(stack.isEmpty()){
                answer[i] = -1;
                stack.addLast(numbers[i]);
            }
            //stack.peek이 number보다 큰 경우
            else if(stack.peekLast() > numbers[i]){
                answer[i] = stack.peekLast();
                stack.addLast(numbers[i]);
            }
            //stack.peek이 number보다 작은 경우
            else{
                while(!stack.isEmpty()&&stack.peekLast()<=numbers[i]){
                    stack.pollLast();
                }
                //stack이 비어버린 경우 -1넣기
                if(stack.isEmpty()){
                    answer[i] = -1;
                }
                else {
                    answer[i] = stack.peekLast();
                }
                stack.addLast(numbers[i]);
            }
        }
        return answer;
    }
}
/*
어차피 자기보다 작은 수가 앞의 수보다 클 일은 없다. 그러니까
뒤에서부터 지나오면서 스택에다 넣어준다.
그렇게 했을때 만약 5라면 스택이 비어있기에 -1을 배열에 넣고 스택에 5추가
3이 나오면 스택의 peek인 5가 3보다 크기에 배열에 5을 넣고 스택에 3을 추가
또 3이 나왔으면 peek이랑 작거나같기에 큰 peek이 나올때까지 pop해주고 5가 나오면 배열에 넣고 스택에 3을 추가
이런식으로 반복을 해준다.
자신보다 peek이 같거나 작을 경우엔 자기보다 큰 peek이 나올때까지 뽑아야하는데 
이때 stack이 비었을때 뽑는거 조심, 그리고 빌때까지 안나오면 -1넣기
*/