import java.util.*;
class Solution {
    static int result;
    static char [] name;
    static String [] datas;
    public int solution(int n, String[] data) {
        result = 0;
        name = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        datas = data;
        makeNM(0, new ArrayList<Character>());
        return result;
    }
    static void makeNM(int len, ArrayList<Character> arr){
        if(len ==8){
            //조건 검사
            for(String x: datas){
                int front = arr.indexOf(x.charAt(0));
                int back = arr.indexOf(x.charAt(2));
                char operand = x.charAt(3);
                int num = x.charAt(4)-'0';
                if((operand=='='&&Math.abs(front-back)-1!=num)||(operand=='<'&&Math.abs(front-back)-1>=num)||(operand=='>'&&Math.abs(front-back)-1<=num)){
                    return;
                }
            }
            result ++;
            return;
        }
        for(int i=0; i<8; i++){
            if(!arr.contains(name[i])){
                arr.add(name[i]);
                makeNM(len+1, arr);
                arr.remove(arr.indexOf(name[i]));
            }
        }
    }
}
// 8!로 일렬로 나열한다. -> 백트레킹으로 조합
// 순서대로 나열한 ArrayList에서 indexOf로 index를 받아와 차이를 구해
// 조건에 부합하는 경우를 static result에 +1씩해준다
// 조건 검사는 제일 마지막에 진행함

