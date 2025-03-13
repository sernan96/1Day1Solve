import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        //그냥 그래프를 그려서 BFS로 하위직원들 전부 칭찬해주기
        int[] workers = new int[N+1];
        int[] parent = new int[N+1];
        
        StringTokenizer worker = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            int boss = Integer.parseInt(worker.nextToken());
            parent[i] = boss;
        }
        for(int i=0; i<M; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            workers[target] += cost;
        }
        for(int i=2; i<=N; i++){
            workers[i]+=workers[parent[i]];
        }
        StringBuilder sb =new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(workers[i] +" ");
        }
        System.out.print(sb);
    }
}