import java.io.*;
import java.util.*;

public class Main{

    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (N--!=0){
            int input =Integer.parseInt(br.readLine());
            if(input==0){
                if(pq.isEmpty()){
                    sb.append(0+"\n");
                }
                else{
                    sb.append(pq.poll()+"\n");
                }
            }
            else {
                pq.add(input);
            }
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}