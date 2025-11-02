import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<int []>> graph = new ArrayList<>();
        HashMap<String, Integer> checkDup = new HashMap<>();
        for(int i=0; i<=V; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            //더 짧은 길이 있다면 갱신, 아니면 넘기기
            if((checkDup.containsKey(a+" "+b)&&checkDup.get(a+" "+b)<=c)||(checkDup.containsKey(b+" "+a)&&checkDup.get(b+" "+a)<=c)){
                continue;
            }
            checkDup.put(a+" "+b, c);
            checkDup.put(b+" "+a, c);
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        if(V==2){
            System.out.print(-1);
            return;
        }
        st = new StringTokenizer(br.readLine());
        int jLoc = Integer.parseInt(st.nextToken());
        int sLoc = Integer.parseInt(st.nextToken());
        PriorityQueue<int []> results = new PriorityQueue<>(Comparator.comparingInt((int [] o1)->o1[0])
                .thenComparingInt(o1->o1[1])
                .thenComparingInt(o1->o1[2]));//[0]에는 지헌이와 성하까지의 위치 합, [1]은 지헌이까지의 거리, [2]에는 해당 위치의 번호

        //각 장소마다 다익스트라 실행
        int result = Integer.MAX_VALUE;
        for(int i=1; i<=V; i++){
            if(jLoc==i||sLoc==i){
                continue;
            }
            int [] dist = new int[V+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;
            PriorityQueue<int []> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1[1]));
            pq.add(new int[]{i, 0});
            while(!pq.isEmpty()){
                int [] cur = pq.poll();
                int num = cur[0];
                for(int [] x: graph.get(num)){
                    int next = x[0];
                    int cost = cur[1]+x[1];
                    if(dist[next]>cost){
                        dist[next] = cost;
                        pq.add(new int[]{next, cost});
                    }
                }
            }
            if(dist[jLoc]!=Integer.MAX_VALUE &&dist[sLoc]!=Integer.MAX_VALUE){
                //System.out.println(i+"노드에서 "+"지헌이까지 위치: "+dist[jLoc]+", 성하까지 위치: "+dist[sLoc]);
                results.add(new int[]{dist[jLoc]+dist[sLoc],dist[jLoc],i});
                result = Math.min(result, dist[jLoc]+dist[sLoc]);
            }
        }

        if(!results.isEmpty()){
            while (!results.isEmpty()&&results.peek()[0]<=result){
                int [] cur = results.poll();
                if(cur[1]<=cur[0]-cur[1]){
                    System.out.print(cur[2]);
                    return;
                }
            }
        }
        System.out.print(-1);
    }
}
/*
지헌이와 성하에게 도달하는 시간의 합이 최소인 출발지를 구해야 한다.
그 말은 각 약속 장소 후보 수 V-2(출발지 제외)번 다익스트라를 돌아서
최소의 결과를 출력하면 된다.
결과는 여러개일 수 도 있기때문에 PriorityQueue를 활용해 저장해주고 마지막에 poll해서 출력한다.
만약 PQ의 size가 0이라면 -1을 출력한다.
 */
