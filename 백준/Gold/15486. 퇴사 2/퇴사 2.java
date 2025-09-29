import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] T = new int[N+2];
        int [] P = new int[N+2];
        int [][] dp = new int[N+2][2];
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int Ti = Integer.parseInt(st.nextToken());
            int Pi = Integer.parseInt(st.nextToken());
            T[i] = Ti;//계산하기 쉽게 당일은 제외
            P[i] = Pi;
        }
        dp[N][0] = 0;
        dp[N][1] = T[N]!=1?0:P[N];
        for(int i=N-1; i>=1; i--){
            dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1]);
            if(i+T[i]<=N+1){//상담가능
                dp[i][1]= Math.max(dp[i+T[i]][1], dp[i+T[i]][0])+P[i];
                //System.out.println("상담가능"+i);
            }
            else{
                dp[i][1]= Math.max(dp[i+1][1], dp[i+1][0]);
            }

        }
        System.out.print(Math.max(dp[1][0], dp[1][1]));
    }
}
/*
dp로 하거나-> 해야함
하거나 안하거나로 N X 2배열을 만듦
dp[i][0] = i번째 상담을 안하는 경우
dp[i][1] = i번째 상담을 하는 경우
문제 특성상 뒤에서부터 거꾸로 하는게 좋아보임


BFS나 다익스트라로 하면 불가능
-> 큐에 {날짜, 누적금액}
해당 날짜에 상담을 잡을 수도 있고 안잡을 수도 있음 그렇게 했을때 안잡는 경우의 수까지 하면
시간내에 절대 안될듯
 */