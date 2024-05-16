import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int [][] sum = new int[N][N];
        for(int i =0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                if(j!=0){
                    sum[i][j]= Integer.parseInt(st.nextToken())+sum[i][j-1];
                }
                else{
                    sum[i][j]= Integer.parseInt(st.nextToken());
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;
            int appendSum=0;
            for(int j=x1; j<=x2; j++){
                if(y1!=0){
                    appendSum+=sum[j][y2]-sum[j][y1-1];
                }
                else{
                    appendSum+=sum[j][y2];
                }
            }
            sb.append(appendSum+"\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}