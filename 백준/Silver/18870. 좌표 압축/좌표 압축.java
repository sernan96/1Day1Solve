import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int [] input = new int[n];
        for(int i =0 ; i<n; i++){
            int x = Integer.parseInt(st.nextToken());
            input[i]=x;
            if(map.containsKey(x)){
                map.put(x,map.get(x)+1);
            }
            else{
                map.put(x, 1);
            }
        }
        int x_prime =0;
        for(Map.Entry<Integer,Integer>entry: map.entrySet()){
            int num =entry.getKey();
            map.put(num, x_prime++);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            sb.append(map.get(input[i])+" ");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}
