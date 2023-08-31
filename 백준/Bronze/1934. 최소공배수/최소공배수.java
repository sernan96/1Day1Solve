import java.io.*;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
        	String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            if(a<b) {//swap
            	int temp=0;
            	temp = a;
            	a=b;
            	b=temp;
            }
            int min=1;
            while((min*b)%a!=0) {
            	
            	min++;
            }
            System.out.println(min*b);	
            min =0;
        }

    }    
}