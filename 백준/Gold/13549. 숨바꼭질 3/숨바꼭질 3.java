import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [] visited = new int[100001];
        ArrayDeque<int []> q = new ArrayDeque<>();
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[N] = 0;
        q.add(new int[]{N, 0});
        while (!q.isEmpty()){
            int [] cur = q.poll();
            int loc = cur[0];
            int time = cur[1];
            if(loc==K){
                System.out.print(time);
                return;
            }
            if(loc*2<=100000 && visited[loc*2]>time){
                q.addFirst(new int[]{loc*2, time});
                visited[loc*2] = time;
            }
            if(loc+1<=100000&&visited[loc+1]>time+1){
                q.addLast(new int[]{loc+1, time+1});
                visited[loc+1] = time+1;
            }
            if(loc-1>=0&&visited[loc-1]>time+1){
                q.addLast(new int[]{loc-1, time+1});
                visited[loc-1] = time+1;
            }
        }
    }
}
/*
DFS로 방문처리만 잘해준다면 빠르게 찾을 수 있을것 같다.
그러나 BFS가 훨씬 빠를듯
*/