import java.io.*;
import java.util.*;

public class Main {
    static int N , K, result;
    static int [][] W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NK = new StringTokenizer(br.readLine());
        N= Integer.parseInt(NK.nextToken());
        K= Integer.parseInt(NK.nextToken());
        result = Integer.MAX_VALUE;
        W= new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                W[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0;j<N; j++){
                for(int k=0;k<N; k++){
                    if(i!=j){
                        W[i][j] = Math.min(W[i][j], W[i][k]+W[k][j]);
                    }
                }
            }
        }
        boolean []visited = new boolean[N];
        visited[K]=true;
        spaceShip(1, 0, visited, K);
        System.out.print(result);
    }
    static void spaceShip(int depth, int time, boolean []visited, int before){
        if(depth==N){
            result=Math.min(result, time);
            return;
        }
        for(int i=0; i<N; i++){
            if(!visited[i]&&before!=i){
                visited[i] = true;
                spaceShip(depth+1, time+W[before][i], visited, i);
                visited[i] = false;
            }
        }
    }
}
