import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        // 입력 및 변수 선언 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int [][] W = new int [V+1][V+1];
        for(int i=0; i<= V; i++){
            Arrays.fill(W[i], Integer.MAX_VALUE);
        }
        while(E--!=0){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            W[start][end] = cost;
        }
        int [][] D;
        D = W;
        for(int k=1; k<=V; k++){
            for(int i=1; i<=V; i++){
                for(int j=1; j<=V; j++){
                    if(i==k||j==k||i==j||D[i][k]==Integer.MAX_VALUE||D[k][j]==Integer.MAX_VALUE){
                        continue;
                    }
                    D[i][j] = Math.min(D[i][j], D[i][k]+D[k][j]);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for(int i=1; i<=V; i++){
            for(int j=1; j<=V; j++){
                int cycle = D[i][j]+D[j][i];
                if(cycle>0){
                    result = Math.min(result, cycle);
                }
            }
        }
        result = result==Integer.MAX_VALUE? -1: result;
        System.out.print(result);
    }
}
/*
일단 플로이드 알고리즘을 한번 돌리고 나온 D 배열을 활용해서 다시 돌아가는 사이클중 최소를 구하면 될것

*/