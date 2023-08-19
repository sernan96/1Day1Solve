import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
        int []arr=new int[26];
        for(int i=0; i<26; i++) {
        	arr[i]=-1;
        }
        char []c = s.toCharArray();
        for(int i=0; i<s.length(); i++) {
        	if(arr[c[i]-'a']==-1) {
        		arr[c[i]-'a']=i;
        	}
        }
        for(int i=0; i<26; i++) {
        	System.out.print(arr[i]+" ");
        }
    }
}