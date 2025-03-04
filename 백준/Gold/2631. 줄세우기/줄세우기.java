import java.io.*;

class Main{
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int [] dp = new int[N];
        int max = Integer.MIN_VALUE;
        dp[0]=1;
        for(int i=1; i<N; i++){
            dp[i]=1;
            for(int j=0; j<i; j++){
                if(arr[j]<arr[i]&&dp[j]+1>dp[i]){
                    dp[i]=dp[j]+1;
                    max = Math.max(dp[i], max);
                }
            }
        }
        System.out.print(N-max);
    }
}