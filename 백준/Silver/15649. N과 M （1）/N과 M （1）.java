import java.io.*;
import java.util.*;

public class Main{
    static List<ArrayList> result = new ArrayList<>();
    static int N, K;
    static StringBuilder sb =new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NK = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NK.nextToken());
        K = Integer.parseInt(NK.nextToken());
        makeNM(new ArrayList<>());
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }
    static void makeNM(ArrayList<Integer> arr){
        if(arr.size()==K){
            for(int x: arr){
                sb.append(x+" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=1; i<=N; i++){
            if(!arr.contains(i)){
                arr.add(i);
                makeNM(arr);
                arr.remove((Integer) i);
            }
        }
    }
}