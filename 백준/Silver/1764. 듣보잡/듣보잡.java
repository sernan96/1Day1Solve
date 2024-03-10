import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Boolean> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        List<String> arr = new ArrayList<>();
        int cnt =0;
        while(n--!=0) {
            map.putIfAbsent(br.readLine(), true);
        }
        while(m--!=0) {
            String str= br.readLine();
            if(map.containsKey(str)){
                arr.add(str);
                cnt++;
            }
        }
        sb.append(cnt+"\n");
        Collections.sort(arr);
        Iterator<String> it = arr.iterator();
        while(it.hasNext()){
            sb.append(it.next()+"\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}
