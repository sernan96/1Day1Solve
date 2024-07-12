import java.io.*;
import java.util.*;

class Main {
    static class pair{
        int weight, value;
        public pair(int weight, int value){
            this.weight=weight;
            this.value=value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //점화식 dp[i][w] =Math.max(dp[i-1][w], dp[i-1][w-wi]+vi);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][]dp= new int[N+1][K+1];
        pair [] pairs = new pair[N+1];
        for(int i=1; i<=N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            pairs[i] = new pair(Integer.parseInt(input.nextToken()),Integer.parseInt(input.nextToken()));
        }
        for(int i =1; i<=N; i++){
            for(int j=1; j<=K; j++){
                if(j<pairs[i].weight){
                    dp[i][j] =dp[i-1][j];
                }
                else{
                    dp[i][j] =Math.max(dp[i-1][j], dp[i-1][j-pairs[i].weight]+pairs[i].value);
                }
            }
        }
        System.out.print(dp[N][K]);
    }
}
