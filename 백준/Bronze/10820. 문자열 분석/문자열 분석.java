import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s= "";
        int length, total=0;
        while((s= br.readLine())!=null) {
        	int []arr= {0,0,0,0};
        	total=0;
        	length = s.length();
        	for(int i=0; i<length; i++) {
        		if(s.charAt(i)==' ') {
        			arr[3]++;
        			total++;
        		}
        		else if(s.charAt(i)-'A'>=0 &&s.charAt(i)-'Z'<=0) {//대문자
        			arr[1]++;
        			total++;
        		}
        		else if(s.charAt(i)-'a'>=0 &&s.charAt(i)-'z'<=0) {//소문자
        			arr[0]++;
        			total++;
        		}
        		else {
        			arr[2]++;
        			total++;
        		}
        	}
        	if(total==0) {
        		return;
        	}
        	for(int i=0; i<4; i++) {
        		if(i==3) {
        			System.out.println(arr[i]);
        		}
        		else {
        			System.out.print(arr[i]+ " ");
        		}
        	}
        }
    }
}