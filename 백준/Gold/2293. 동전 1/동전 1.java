import java.io.*;
import java.util.*;

public class Main {
    static int M;
    static ArrayList<Integer> coin = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while (N--!=0){
            coin.add(Integer.parseInt(br.readLine()));
        }
        int []dp = new int[M+1];
        dp[0] = 1;
        for (int x: coin){
            for(int i = x; i<=M; i++){
                dp[i] += dp[i-x];
            }
        }
        System.out.print(dp[M]);
    }
}