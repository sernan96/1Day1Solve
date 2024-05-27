import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int P[][];
    static int n, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());
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
        P = new int[n+1][n+1];
        for(int k=1; k<=n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(D[i][j]> D[i][k] + D[k][j]){
                        D[i][j]=D[i][k] + D[k][j];
                        P[i][j] =k;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(D[i][j]>=Integer.MAX_VALUE){
                    sb.append(0+ " ");
                }
                else{
                    sb.append(D[i][j]+ " ");
                }
            }
            sb.append("\n");
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(D[i][j]!=0&&(D[i][j]<Integer.MAX_VALUE)){
                    StringBuilder Past = findWay(i, j);
                    sb.append((2+cnt)+" "+i+" "+Past+j+"\n");
                    cnt=0;
                }
                else{
                    sb.append(0+"\n");
                }
            }
        }
        System.out.print(sb);
    }
    static StringBuilder findWay(int x, int y) {
        StringBuilder way = new StringBuilder();
        if (P[x][y] == 0) {
            return way;
        }
        int k = P[x][y];
        way.append(findWay(x, k)).append(k).append(" ").append(findWay(k, y));
        cnt++;
        return way;
    }
}