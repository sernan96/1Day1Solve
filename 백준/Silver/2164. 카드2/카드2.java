import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            dq.addLast(i);
        }
        while (dq.size()!=1){
            dq.pollFirst();
            int temp = dq.pollFirst();
            dq.addLast(temp);
        }
        System.out.print(dq.poll());
    }
}
