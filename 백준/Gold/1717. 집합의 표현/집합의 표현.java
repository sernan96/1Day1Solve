import java.io.*;
import java.util.*;

public class Main {
    static int [] set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer nm = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(nm.nextToken());
        int M = Integer.parseInt(nm.nextToken());
        set = new int[N+1];
        for(int i=1; i<=N; i++){
            set[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int func =Integer.parseInt(input.nextToken());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            if(func==0){
                union(a, b, 0);
            }
            else{
                sb.append(union(a, b, func)? "YES":"NO").append("\n");
            }
        }
        System.out.print(sb);
    }
    static int find(int parent){
        if(parent==set[parent]){
            return parent;
        }
        return set[parent]=find(set[parent]);
    }
    static boolean union(int x, int y, int func){
        int xP = find(x);
        int yP = find(y);
        if(xP==yP){//이미 같은 집합
            return true;
        }
        if(func==0){
            if(xP>yP){// 인덱스가 작은 부모를 우선순위로
                set[xP]= yP;
            }
            else {
                set[yP] = xP;
            }
        }
        return false;
    }
}
