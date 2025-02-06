import java.io.*;
import java.util.*;

public class Main {
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        if(N%2!=0){
            System.out.print(0);
            return;
        }
        long [] arr = new long[31];
        arr[0] = 1;
        arr[2] = 3;
        arr[4] = arr[2]*3+2;
        for(int i=4; i<=N; i+=2){
            arr[i] =arr[i-2]*3;
            for(int j = i-4; j>=0; j-=2){
                arr[i]+=arr[j]*2;
            }
        }
        System.out.print(arr[N]);
    }
}