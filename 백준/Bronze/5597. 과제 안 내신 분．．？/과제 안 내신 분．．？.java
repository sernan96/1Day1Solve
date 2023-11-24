import java.io.*;
import java.lang.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean []arr = new boolean[30];
		for(int i=0; i<28; i++) {
			arr[Integer.parseInt(br.readLine())-1]= true;
		}
		int answer=0;
		for(boolean right:arr) {
			if(!right) {
				System.out.println(answer+1);
			}
			answer++;
		}
			
	}
}