import java.io.*;
import java.util.*;

public class Main {
    static int [] arr;
    static int N;
    static int min=Integer.MAX_VALUE;
    static int max=Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer items = new StringTokenizer(br.readLine());
        int [] item =new int[4];//덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수
        for(int i=0; i<4; i++){
            item[i]=Integer.parseInt(items.nextToken());
        }
        insert(1, arr[0], item);
        System.out.print(max+"\n"+min);
    }
    static void insert(int i, int result, int [] item){
        if(i==N){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }
        for(int j=0; j<4; j++){
            if(item[j]>0){
                item[j]--;
                switch (j){
                    case 0:
                        insert(i+1,result+arr[i], item);
                        break;
                    case 1:
                        insert(i+1,result-arr[i], item);
                        break;
                    case 2:
                        insert(i+1,result*arr[i], item);
                        break;
                    case 3:
                        if(result<0){
                            result*=-1;
                            result/=arr[i];
                            result*=-1;
                            insert(i+1,result, item);
                        }
                        else{
                            insert(i+1,result/arr[i], item);
                        }
                        break;
                }
                item[j]++;
            }
        }
    }
}