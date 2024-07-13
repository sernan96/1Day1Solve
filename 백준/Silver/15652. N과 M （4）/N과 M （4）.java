import java.io.*;
import java.util.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>list =new ArrayList<>();
        for(int i=1; i<=n; i++){
            list.add(i);
            make(n, m, list);
            list.clear();
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    static void make (int n, int m,List<Integer> list){
        if(list.size()==m){
            for (int x: list){
                sb.append(x+" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=1; i<=n; i++){
            if(list.get(list.size() - 1)<=i){
                list.add(i);
                make(n, m, list);
                list.remove(list.size()-1);
            }
        }
    }
}
