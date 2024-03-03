import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Node>> graph;
    static int [] dist;
    static class Node{
        int idx, cost;
        Node(int idx, int cost){
            this.idx =idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        graph = new ArrayList<ArrayList<Node>>();
        for(int i =0; i<n+1; i++){
            graph.add(new ArrayList<Node>());
        }
        for(int i =0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end,money));
        }
        dist =new int [n+1];
        for(int i =0; i<n+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0]= 0;
        PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start_result = Integer.parseInt(st.nextToken());
        int end_result = Integer.parseInt(st.nextToken());

        q.offer(new Node(start_result,0));

        while (!q.isEmpty()){
            Node cur_Node = q.poll();
            if(dist[cur_Node.idx]<cur_Node.cost){
                continue;
            }
            for(int i=0; i< graph.get(cur_Node.idx).size(); i++){
                Node next_Node = graph.get(cur_Node.idx).get(i);
                if(dist[next_Node.idx]> cur_Node.cost+ next_Node.cost){
                    dist[next_Node.idx] =cur_Node.cost+ next_Node.cost;
                    q.offer(new Node(next_Node.idx, dist[next_Node.idx]));
                }
            }
        }
        System.out.print(dist[end_result]);
    }
}
