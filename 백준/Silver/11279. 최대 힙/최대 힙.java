import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer>maxH = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int input = Integer.parseInt(br.readLine());
            if(input!=0){
                maxH.add(input);
            }
            else{
                if(maxH.isEmpty()){
                    sb.append("0");
                }
                else {
                    sb.append(maxH.poll());
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}