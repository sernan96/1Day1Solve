import java.io.*;
import java.util.*;

public class Main {
    static int [] num;
    static int N,M;
    static StringBuilder sb =new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        num = new int[N];
        M = Integer.parseInt(st.nextToken());
        StringTokenizer input = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(input.nextToken());
        }
        Arrays.sort(num);
        makeNM(new ArrayList<Integer>());
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    static void makeNM(ArrayList<Integer> arr){
        if(arr.size()==M){
            for (int x: arr){
                sb.append(x+" ");
            }
            sb.append("\n");
            return;
        }
        for (int i=0; i<N; i++){
            if(!arr.contains(num[i])){
                arr.add(num[i]);
                makeNM(new ArrayList<>(arr));
                arr.remove((Integer) num[i]);
            }
        }
    }
}