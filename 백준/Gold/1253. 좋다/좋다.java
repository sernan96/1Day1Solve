import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        if(arr.length==1){
            System.out.print('0');
            return;
        }
        int cnt=0;
        for(int i=0; i<N; i++){

            int start = 0;
            int end = N-1;
            while(true){
                if(i == start){
                    start++;
                }
                else if(i==end){
                    end--;
                }
                if(start>=end){
                    break;
                }
                if(arr[start]+arr[end]>arr[i]){
                    end--;
                }
                else if(arr[start]+arr[end]<arr[i]){
                    start++;
                }
                else{
                    cnt++;
                    break;
                }
            }
        }
        System.out.print(cnt);
    }
}