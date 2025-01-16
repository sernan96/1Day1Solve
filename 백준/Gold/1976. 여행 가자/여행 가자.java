import java.io.*;
import java.util.*;

public class Main {
    static int [] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        if(M==0){
            System.out.print("YES");
            return;
        }
        parent = new int[N+1];
        for(int i=1; i<N+1; i++){
            parent[i] = i;
        }
        for(int i=1; i<=N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                int num = Integer.parseInt(input.nextToken());
                if(i<j&&num==1){
                    //union
                    union(i, j);
                }
            }
        }
        StringTokenizer plan = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(plan.nextToken()));
        while (plan.hasMoreTokens()){
            int test = Integer.parseInt(plan.nextToken());
            if(find(test)!=start){
                System.out.print("NO");
                return;
            }
        }
        System.out.print("YES");
    }
    static int find (int node){
        if(node==parent[ node]){
            return node;
        }
        return parent[node] = find(parent[node]);
    }
    static void union(int x, int y){
        int xP = find(x);
        int yP = find(y);
        if(xP>yP) {
            parent[xP] = yP;
        }
        else if(xP<yP){
            parent[yP] = xP;
        }
    }

}
