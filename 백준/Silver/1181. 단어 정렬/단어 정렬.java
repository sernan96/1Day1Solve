import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String [] str = new String[N];
        int cnt = 0;
        for(int i =0; i<N; i++){
            str[i]= br.readLine();
        }
        if(N==1){
            System.out.print(str[0]);
            return;
        }
        Arrays.sort(str, (o1, o2)-> {
            if (o1.length() != o2.length()) {
                return Integer.compare(o1.length(), o2.length());
            } else {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(str[0]+"\n");
        for(int i =1; i<N; i++){
            if(!str[i-1].equals(str[i])){
                sb.append(str[i]+"\n");
            }
        }
        System.out.print(sb);
    }
}