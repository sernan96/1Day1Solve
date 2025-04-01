
import java.io.*;
import java.util.*;

class Main{
    public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [][] map = new long [N][N];
        long [][] dp = new long [N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st =new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                long x = Long.parseLong(st.nextToken());
                map[i][j] = x;
            }
        }
        dp[0][0] = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i+map[i][j]<N&&map[i][j]!=0){
                    dp[(int) (i+map[i][j])][j]+=dp[i][j];
                }
                if(j+map[i][j]<N&&map[i][j]!=0){
                    dp[i][(int) (j+map[i][j])]+=dp[i][j];
                }
            }
        }
        System.out.print(dp[N-1][N-1]);
    }
}