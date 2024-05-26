import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        int bus = Integer.parseInt(br.readLine());
        long [][] W = new long[n+1][n+1];
        long [][] D;
        while(bus--!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(W[i][j]!=0){
                W[i][j]= Math.min(W[i][j], cost);
            }
            else{
                W[i][j]= cost;
            }
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if((i!=j)&&W[i][j]==0){//바로가는 경우가 안주어졌을때 0으로 되어있음
                    W[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        D= W;
        for(int k=1; k<=n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(D[i][j]>=Integer.MAX_VALUE){
                    System.out.print(0+ " ");
                }
                else{
                    System.out.print(D[i][j]+ " ");
                }
            }
            System.out.println();
        }
    }
}