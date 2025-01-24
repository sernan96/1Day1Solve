import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1)==Math.abs(o2)){
                    return o1-o2;
                }
                return Math.abs(o1)-Math.abs(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        while(N--!=0){
            int x = Integer.parseInt(br.readLine());
            if(x==0){
                sb.append(pq.isEmpty()?0:pq.poll()).append("\n");
            }
            else{
                pq.add(x);
            }
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

}
