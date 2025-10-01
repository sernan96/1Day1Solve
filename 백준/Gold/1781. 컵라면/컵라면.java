import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int []> pq = new PriorityQueue<>(Comparator.comparingInt((int [] o1)->o1[0]).thenComparingInt(o1->o1[1]*(-1)));
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }
        int result = 0;
        PriorityQueue<int []> st = new PriorityQueue<>(Comparator.comparingInt(o1->o1[1]));
        while (!pq.isEmpty()){
            int [] cur = pq.poll();
            if(cur[0]<=st.size()){
                if(st.peek()[1]<cur[1]){
                    int [] temp = st.poll();
                    //System.out.println(temp[1]+"개");
                    st.add(cur);
                }
                continue;
            }
            st.add(cur);
        }
        for(int []x: st){
            result+=x[1];
        }
        System.out.print(result);
    }
}
/*
저번에 풀었던 dp문제에 순서를 내가 조합해서 주는 건가라고 생각했는데
그렇게 하면 20만!만큼 dp를 수행하게 되는데 애초에 조합자체도 너무 많기에 이방법은 아니다.

그렇다고 한다면 데드라인 기준으로 정렬
데드라인이 같다면 컵라면 많은 순으로 정렬한다.
그렇게 하나씩 빼면서 문제를 풀어낸다.
여기서 그리디로 최대 컵라면 수를 계산한다는게 내 계획
*/