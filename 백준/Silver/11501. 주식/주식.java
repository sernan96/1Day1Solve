import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T--!=0){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for(int i=0; i<N; i++){
                stack.add(Integer.parseInt(st.nextToken()));
            }
            long result = 0;
            int sellPrice = 0;
            while (!stack.isEmpty()){
                //sellPrice보다 스택 top이 큰 경우
                if(stack.peekLast()>sellPrice){
                    sellPrice = stack.pollLast();
                }
                //sellPrice보다 스택 top이 작은 경우
                else{
                    int buyPrice = stack.pollLast();
                    result+=sellPrice-buyPrice;
                }
            }
            sb.append(result+"\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}
/*
우측에 위치한 가장큰 수에서 판매를 하면 된다.
stack으로 각 날짜대로 받은 다음
하나씩 빼며 sellPrice를 갱신해준다.
그렇게 한다고 했을때
2가지의 상황이 존재
1. sellPrice보다 스택 top이 큰 경우 -> sellPrice에 스택 top반영
2. sellPrice보다 스택 top이 작은 경우 -> sellPrice에 가격에 맞게 차액 + 해주기
 */
