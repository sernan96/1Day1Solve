import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] arr = new int[N][3];
        int [][] dp = new int[N][3];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        int result =Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            // R G B 순서임
            for(int j=0; j<3; j++){
                dp[0][j] = (i==j)? arr[0][j] : 1000000;
            }
            for(int j=1; j<N; j++){
                dp[j][0] =Math.min(dp[j-1][1], dp[j-1][2])+arr[j][0];
                dp[j][1] =Math.min(dp[j-1][0], dp[j-1][2])+arr[j][1];
                dp[j][2] =Math.min(dp[j-1][0], dp[j-1][1])+arr[j][2];
            }
            for(int j=0; j<3; j++){
                if(j!=i){
                    result=Math.min(result, dp[N-1][j]);
                }
            }
        }
        System.out.print(result);
    }
}
