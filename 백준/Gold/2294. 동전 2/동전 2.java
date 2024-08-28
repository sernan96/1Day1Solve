import java.io.*;
import java.util.*;

public class Main {
    static class pair{
        int money;
        int cnt;
        public pair(int money, int cnt){
            this.money = money;
            this.cnt = cnt;
        }
    }
    static int k;
    static Set<Integer> coin = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        while(n--!=0){
            coin.add(Integer.parseInt(br.readLine()));
        }
        BFS();
    }
    static void BFS(){
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(0, 1));
        Set <Integer> visited = new HashSet<>();
        while (!q.isEmpty()){
            pair current = q.poll();
            for(int x: coin){
                int cm = current.money + x;
                if(visited.contains(cm)){
                    continue;
                }
                else {
                    visited.add(cm);
                }
                if(cm==k){
                    System.out.print(current.cnt);
                    return;
                } else if (cm<k) {
                    q.add(new pair(cm, current.cnt+1));
                }
            }
        }
        System.out.print(-1);
    }
}