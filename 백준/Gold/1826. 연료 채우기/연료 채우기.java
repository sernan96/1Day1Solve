import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int [] > gasStations = new ArrayList<>();
        for(int i=0; i<N; i++){
            StringTokenizer gasStation = new StringTokenizer(br.readLine());
            int loc = Integer.parseInt(gasStation.nextToken());
            int fuel = Integer.parseInt(gasStation.nextToken());
            gasStations.add(new int[]{loc, fuel});
        }
        gasStations.sort(Comparator.comparingInt(o1 -> o1[0]));
        StringTokenizer LP = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(LP.nextToken());
        int P = Integer.parseInt(LP.nextToken());

        int cnt = 0;
        int index = 0;
        int loc = P;
        PriorityQueue<int []> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1[1]*(-1)));
        while(loc < L){
            while (index<N && gasStations.get(index)[0]<=loc){
                pq.add(gasStations.get(index));
                index++;
            }
            if(pq.isEmpty()){
                System.out.print("-1");
                return;
            }
            int [] cur = pq.poll();
            loc+= cur[1];
            cnt++;
        }
        System.out.print(cnt);
    }
}
/*
BFS로 방문 또는 방문안하는 노드들을 넣어주면 될거같다.
*/