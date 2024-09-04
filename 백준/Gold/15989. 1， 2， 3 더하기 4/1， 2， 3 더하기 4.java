import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] pNum = {1, 2, 3};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T--!=0){
            int n = Integer.parseInt(br.readLine());
            plus(n);
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    static void plus(int n){
        int []dp = new int[n+4];
        dp[0]=1;
        for(int x: pNum){
            for(int i=x; i<=n; i++){
                dp[i]+=dp[i-x];
            }
        }
        sb.append(dp[n]+"\n");
    }
}
