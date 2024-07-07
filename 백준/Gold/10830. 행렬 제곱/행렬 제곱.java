import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int [][] arr = new int[N][N];
        int [][] result = new int[N][N];
        for(int i =0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(input.nextToken())%1000;
            }
        }
        for(int i=0; i<N; i++){
            result[i][i]=1;
        }
        if(B==1){
            StringBuilder sb = new StringBuilder();
            for(int i =0; i<N; i++){
                for(int j=0; j<N; j++){
                    sb.append(arr[i][j]+ " ");
                }
                sb.append("\n");
            }
            sb.setLength(sb.length()-1);
            System.out.print(sb);
            return;
        }
        while(B>0){
            if(B%2!=0){//한번 곱
                result= gop(result, arr);
            }
            B=B/2;
            arr=gop(arr, arr);
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<N; i++){
            for(int j=0; j<N; j++){
                sb.append(result[i][j]+ " ");
            }
            sb.append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    static int [][] gop(int [][]a, int [][]b){
        int [][]ret = new int[a.length][a.length];
        for(int i =0; i<a.length; i++){
            for(int j=0; j<a.length; j++){
                for(int k = 0; k<a.length; k++){
                    ret[i][j] += a[i][k]*b[k][j];
                }
                ret[i][j]%=1000;
            }
        }
        return ret;
    }
}
