
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> arr = new ArrayList<>();
        String s = br.readLine();
        StringBuffer sb = new StringBuffer(s);
        for(int i=0; i<s.length(); i++) {
        	arr.add(sb.substring(i, s.length()).toString());
        }
        Collections.sort(arr);
        for(String x:arr) {
        	System.out.println(x);
        }
    }    
}