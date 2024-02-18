import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static boolean nulll=false;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result  = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while(t--!=0){
            char []command = br.readLine().toCharArray();
            n = Integer.parseInt(br.readLine());
            char []arr = br.readLine().toCharArray();//괄호 + 숫자
            Deque<Integer> dq = getIntegers(arr);
            boolean front= true, is_err=false;

            for(char x: command){
                if(x =='R'){ //뒤집기 수행
                    front=!front;
                }
                else {//delete 수행
                    if(nulll){
                        result.append("error\n");
                        is_err = true;
                        break;
                    }if(dq.isEmpty()){
                        result.append("error\n");
                        is_err = true;
                        break;
                    }
                    if(!front){
                        dq.pollFirst();
                    }
                    else{
                        dq.pollLast();
                    }
                }
            }
            //결과 정리
            if(nulll&&!is_err){
                result.append("[]\n");
                continue;
            }
            if(!is_err){
                result.append("[");
                if(!front){
                    while(!dq.isEmpty()){
                        result.append(dq.pollFirst()).append(",");
                    }
                }
                else{
                    while(!dq.isEmpty()){
                        result.append(dq.pollLast()).append(",");
                    }
                }
                if(result.charAt(result.length()-1)==','){
                    result.deleteCharAt(result.length()-1);
                }
                result.append("]\n");
            }
            nulll=false;
        }
        result.deleteCharAt(result.length()-1);
        System.out.println(result);
    }

    private static Deque<Integer> getIntegers(char[] arr) {
        StringBuilder sb  = new StringBuilder();
        if(n==0){
            nulll=true;
            return null;
        }
        //숫자만 따로 전처리
        for(char num: arr){
            if(num!=']'&& num!='['){
                if(num!=','){
                    sb.append(num);
                }
                else{
                    sb.append(" ");
                }
            }
        }
        StringTokenizer st = new StringTokenizer(sb.toString());
        Deque<Integer> dq = new LinkedList<>();
        while (n--!=0){
            dq.push(Integer.parseInt(st.nextToken()));
        }
        return dq;
    }
}