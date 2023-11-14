import java.io.*;
import java.util.StringTokenizer;
public class Main{
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int min =1000001, max=-1000001;
		for(int i=0; i<t; i++) {
			int n =Integer.parseInt(br.readLine());
			StringTokenizer tk = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int a=Integer.parseInt(tk.nextToken());
				if(a>max) {
					max=a;
				}
				if(a<min) {
					min=a;
				}
			}
			System.out.println(min+" "+max);
			min =1000001;
			max=-1000001;
		}
		
		
	}
}