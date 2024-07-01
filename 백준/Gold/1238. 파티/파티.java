import java.io.*;
import java.util.*;

public class Main {
    static int [] parent;
    static Set <Integer> knowPeople= new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int [][] W = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j){
                    W[i][j]=0;
                }
                else{
                    W[i][j] =Integer.MAX_VALUE/2;
                }
            }
        }
        while(M--!=0){
            StringTokenizer road = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(road.nextToken());
            int end = Integer.parseInt(road.nextToken());
            int time = Integer.parseInt(road.nextToken());
            W[start][end]=time;
        }
        for(int k=1; k<=N;k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    W[i][j] =Math.min(W[i][j], W[i][k]+W[k][j]);
                }
            }
        }
        int max =-1;
        for(int i=1; i<=N; i++){
            if(W[i][X]!=Integer.MAX_VALUE&&W[X][i]!=Integer.MAX_VALUE){
                max =Math.max(W[i][X]+W[X][i], max);
            }
        }
        System.out.print(max);
    }
}
