import java.io.*;
import java.util.*;

import static javax.swing.text.html.HTML.Attribute.N;

public class Main{
    static long []houseNum;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NQ = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NQ.nextToken());
        int Q = Integer.parseInt(NQ.nextToken());
        StringTokenizer House = new StringTokenizer(br.readLine());
        houseNum= new long[N+1];
        for(int i=1; i<=N; i++){
            int house = Integer.parseInt(House.nextToken());
            houseNum[i]=house;
        }
        while (Q--!=0){
            StringTokenizer LR = new StringTokenizer(br.readLine());
            getLR(Integer.parseInt(LR.nextToken()), Integer.parseInt(LR.nextToken()));
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
     }

    static void getLR(int left, int right) {// 1 3 5 10 11  LR 2 2
        int l=1, r=N;
        boolean breaker = true;
        while(breaker){
            breaker = false;
            if(left>houseNum[l]){
                l++;
                breaker = true;
            }
            if(right<houseNum[r]){
                r--;
                breaker = true;
            }
            if (l >= r) {
                sb.append(0+"\n");
                return;
            }
        }
        //이제 해당 부분 부분 정렬로  큰수부터 앞뒤로 빼서 절대값 구해서 result에 append
        getHardness(calCost(l, r));
    }
    static void getHardness(long [] dp){
        Arrays.sort (dp);
        long result = 0;
        for(int i=0; i<dp.length-1; i++){
            result += Math.abs(dp[i]-dp[i+1]);
        }
        sb.append(result+"\n");
    }

    static long[] calCost(int l, int r){
        long [] dp = new long[N+1];
        for(int i=l; i<=r; i++){
            for(int j=i+1; j<=r; j++) {
                dp[i]+=Math.abs(houseNum[i]-houseNum[j]);
                dp[j]+=Math.abs(houseNum[i]-houseNum[j]);
            }
        }
        return Arrays.copyOfRange(dp, l, r+1);
     }
}