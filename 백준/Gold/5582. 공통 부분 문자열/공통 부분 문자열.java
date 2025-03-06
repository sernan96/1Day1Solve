import java.io.*;

class Main{
    public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        int aLen = a.length();
        String b = br.readLine();
        int bLen = b.length();
        int [][] dp = new int [aLen+1][bLen+1];
        int max =Integer.MIN_VALUE;
        for(int i=1; i<=aLen; i++){
            for(int j=1; j<=bLen; j++){
                if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.print(max==Integer.MIN_VALUE? 0: max);
    }
}