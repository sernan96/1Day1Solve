import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T--!=0){
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            StringTokenizer coin = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                coins[i]= Integer.parseInt(coin.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int [] dp = new int[M+1];
            dp[0]=1;
            for(int x: coins){
                for(int i=x; i<=M; i++){
                    dp[i]+=dp[i-x];
                }
            }
            sb.append(dp[M]+"\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}
