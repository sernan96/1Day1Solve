import java.io.*;
import java.util.*;

public class Main {
    static class document {
        int index, importance;
        public document(int index, int importance){
            this.index = index;
            this.importance = importance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            StringTokenizer NM = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(NM.nextToken());
            int M = Integer.parseInt(NM.nextToken());
            StringTokenizer input = new StringTokenizer(br.readLine());
            ArrayDeque<document> printer = new ArrayDeque<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for(int j=0; j<N; j++){
                int now = Integer.parseInt(input.nextToken());
                printer.addLast(new document(j, now));
                pq.add(now);
            }
            int result =1;
            while (!pq.isEmpty()){
                int cur_importance = pq.poll();//우선적으로 처리해야 할 중요도 번호
                //찾을때까지 뒤로 미루기
                while(!printer.isEmpty()){
                    document first = printer.pollFirst();
                    if(first.importance==cur_importance){//그냥 처리하고 순서++
                        //System.out.println(first.index+"뽑음");
                        if(first.index==M){
                            sb.append(result+"\n");
                        }
                        result++;
                        break;
                    }
                    else{//뒤에 갖다박기
                        printer.addLast(new document(first.index, first.importance));
                    }
                }
            }
        }
        System.out.print(sb);
    }
}
