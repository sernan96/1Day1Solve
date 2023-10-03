
import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;
public class Main{
    public static void main(String[] args)throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	HashMap<Integer, Integer> cnt = new HashMap<>();
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st1 = new StringTokenizer(br.readLine());
    	for(int i= 0; i<n; i++) {
    		int tmp = Integer.parseInt(st1.nextToken());
    		if(cnt.containsKey(tmp)) {
    			cnt.replace(tmp, cnt.get(tmp)+1);
    		}
    		else{
    			cnt.put(tmp, 1);
    		}
    	}
    	int m = Integer.parseInt(br.readLine());
    	StringTokenizer st2 = new StringTokenizer(br.readLine());
    	for(int i= 0; i<m; i++) {
    		int tmp = Integer.parseInt(st2.nextToken());
    		if(cnt.containsKey(tmp)) {
    			sb.append(cnt.get(tmp)+" ");
    		}
    		else{
    			sb.append("0 ");
    		}
    	}
    	System.out.print(sb);
    }    
}