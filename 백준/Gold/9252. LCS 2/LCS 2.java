import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int [][]dp;
    static String a,b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        if(a.length()==1&&b.length()==1){
            if(a.equals(b)){
                System.out.print(1+"\n"+a);
                return;
            }
            else{
                return;
            }
        }
        dp = new int[a.length()+1][b.length()+1];
        for (int i= 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        findSameSTR(a.length(),b.length());
        System.out.println(dp[a.length()][b.length()]);
        System.out.print(sb.reverse());
    }
    static void findSameSTR(int i, int j){
        if(i<1||j<1) {
            return;
        }
        if(dp[i-1][j]==dp[i][j]){
            findSameSTR(i-1, j);
        }
        else if(dp[i][j-1]==dp[i][j]){
            findSameSTR(i, j-1);
        }
        else{
            sb.append(b.charAt(j-1));
            findSameSTR(i-1, j-1);
        }
    }
}
//ACAA
//AAACBA