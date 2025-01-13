import java.io.*;
import java.util.*;

public class Main {
    static int [] weight;
    static int N, K, result;
    static boolean []visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weight = new int[N];
        visited=  new boolean[N];
        result=0;
        StringTokenizer w = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            weight[i] = Integer.parseInt(w.nextToken());
        }
        find(1, 500);
        System.out.print(result);
    }
    static void find(int depth, int power){
        if(depth==N){
            result++;
            return;
        }
        for(int i=0; i<N; i++){
            if(!visited[i]&& power>=500){
                visited[i] = true;
                find(depth+1, power+weight[i]-K);
                visited[i]=false;
            }
        }
    }
}
