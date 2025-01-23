import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int x, cnt ;
        public Node(int x, int cnt){
            this.x = x;
            this.cnt=cnt;
        }
    }
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> visited = new HashMap<>();//위치와 count 저장
        HashMap<Integer, Integer> time = new HashMap<>();// 도착했을 때의 count와 그 시간의 경우의 수 저장
        ArrayDeque<Node> q = new ArrayDeque<>();
        visited.put(N, 0);
        int result_time = Integer.MAX_VALUE;
        q.add(new Node(N, 0));
        while (!q.isEmpty()){
            Node cur = q.poll();
            if(cur.x==K && cur.cnt<=result_time){
                if(time.containsKey(cur.cnt)){
                    time.put(cur.cnt, time.get(cur.cnt)+1);
                }
                else{
                    time.put(cur.cnt, 1);
                }
                result_time = Math.min(result_time, cur.cnt);
                continue;
            }//+1칸 이동
            if(cur.x+1<=100000 && (!visited.containsKey(cur.x + 1) || visited.get(cur.x + 1) >= cur.cnt + 1)){//+1칸 이동
                q.add(new Node(cur.x+1, cur.cnt+1));
                visited.put(cur.x+1, cur.cnt+1);
            }//-1칸 이동
            if(cur.x-1>=0 &&(!visited.containsKey(cur.x-1) || visited.get(cur.x-1)>= cur.cnt+1)){
                q.add(new Node(cur.x-1, cur.cnt+1));
                visited.put(cur.x-1, cur.cnt+1);
            }//2배 이동
            if (cur.x*2<=100000 && (!visited.containsKey(cur.x*2) || visited.get(cur.x*2)>= cur.cnt+1)){
                q.add(new Node(cur.x*2, cur.cnt+1));
                visited.put(cur.x*2, cur.cnt+1);
            }
        }
        System.out.println(result_time);
        System.out.print(time.get(result_time));
    }
}
//BFS?