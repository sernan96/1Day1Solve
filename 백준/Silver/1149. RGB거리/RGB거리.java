import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][]dp = new int [N][3];
        for(int i =0; i<N; i++){
            String [] input = br.readLine().split(" ");
            dp[i][0]= Integer.parseInt(input[0]);
            dp[i][1]= Integer.parseInt(input[1]);
            dp[i][2]= Integer.parseInt(input[2]);
        }
        for(int i =1; i<N; i++){
            dp[i][0]+= Math.min(dp[i-1][1],dp[i-1][2]);
            dp[i][1]+= Math.min(dp[i-1][0],dp[i-1][2]);
            dp[i][2]+= Math.min(dp[i-1][0],dp[i-1][1]);
        }
        System.out.print(Math.min((Math.min(dp[N-1][0],dp[N-1][1])),dp[N-1][2]));
    }

}
