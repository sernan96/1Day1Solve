import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> idpw = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        while(n--!=0){
            StringTokenizer input= new StringTokenizer(br.readLine());
            idpw.put(input.nextToken(), input.nextToken());
        }
        while(m--!=0){
            String target = br.readLine();
            sb.append(idpw.get(target)+"\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

}
