import java.io.*;
import java.util.*;

public class Main{

    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Long> lan = new ArrayList<>();
        long max =Long.MIN_VALUE;
        for(int i=0; i<N; i++){
            long input = Long.parseLong(br.readLine());
            lan.add(input);
            max = Math.max(max, input);
        }
        long low = 1;
        long high = max;
        long answer =0;
        while(low<=high){
            long middle =(low+high)/2;
            long partition=0;
            for(long x: lan){
                partition+=x/middle;
            }if(partition>=K){
                answer=middle;
                low =middle+1;
            }
            else{
                high=middle-1;
            }
        }
        System.out.print(answer);
    }
}