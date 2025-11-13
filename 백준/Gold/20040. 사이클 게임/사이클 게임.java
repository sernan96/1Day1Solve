import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for (int i=0; i<=N; i++){
            parent[i]  =i;
        }
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(union(start, end)){
                System.out.print(i);
                return;
            }
        }
        System.out.print(0);
    }
    static int find(int x){
        if(x==parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    static boolean union(int x, int y){
        int xP = find(x);
        int yP = find(y);
        if(xP == yP){
            return true;
        }
        else if(xP<yP){
            parent[yP] = xP;
        }
        else {
            parent[xP] = yP;
        }
        return false;
    }
}
/*
중점
사이클 생겼는지 판단 어떻게 할지

선분 추가하고 나서
해당 그래프의 원소의 수 만큼 사이클 검사를 돌면 매 턴마다 최대 50만번 사이클검사를 돌게 되고
그게 100만번 반복됨으로 그냥 단순하게 해선 시간초과가 무조건 뜨게된다.
그렇다면 해당 선분이 추가된 집합에 한해서 사이클 검사를 돌아주면 될것이다.
DFS로 기준 노드에 연결된 노드로 갔을때 기준 노드에 도착하면 사이클이 생겼다고 볼 수 있다.
51분 32초
*/
