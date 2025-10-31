import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<int []> info = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            info.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }
        ArrayDeque<int []> q = new ArrayDeque<>();
        int [] visited = new int[C+1002];
        Arrays.fill(visited, Integer.MAX_VALUE);
        q.add(new int[]{0, 0});// 비용, 고객 수
        while (!q.isEmpty()){
            int [] cur = q.poll();
            int cost = cur[0];
            int customer = cur[1];
            if(customer>=C){
                visited[C] = Math.min(cost, visited[C]);
                continue;
            }
            for (int [] x: info){
                int dx = x[0]+cost;
                int dy = x[1]+customer;
                if(dy<=C+1001 && visited[dy]>dx){
                    visited[dy] = dx;
                    q.add(new int []{dx, dy});
                }
            }
        }
        System.out.print(visited[C]);
    }
}
/*
C만큼의 고객을 확보하기 위해서 드는 돈의 최솟값을 구해야한다.
BFS를 통해서 visited[고객 수]에 비용으로 방문처리를 해서
마지막에 visited[C]의 값을 출력해주면 된다.
 */
