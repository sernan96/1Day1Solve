import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N==1){
            System.out.print("1");
            return;
        }
        ArrayDeque <Integer> dq = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            dq.addLast(i);
        }
        while (dq.size()>1){
            System.out.print(dq.pollFirst()+" ");
            int temp = dq.pollFirst();
            dq.addLast(temp);
        }
        System.out.print(dq.poll()) ;
    }

}
