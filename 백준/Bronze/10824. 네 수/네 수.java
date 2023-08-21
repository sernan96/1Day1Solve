import java.io.*;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long []arr = new long [4];
        long total = 0;
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
        	arr[i] = Long.parseLong(st.nextToken());
        	if(i==1) {
        		sb.append(arr[0]);
        		sb.append(arr[1]);
        		total+=Long.parseLong(sb.toString());

        		sb.setLength(0);
        	}
        	else if(i==3) {
        		sb.append(arr[2]);
        		sb.append(arr[3]);

        		total+=Long.parseLong(sb.toString());
        	}
        }
        System.out.print(total);
    }    
}