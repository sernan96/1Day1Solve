import java.io.*;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int []arr = new int[n];
        int max =0;
        for(int i=0; i<n; i++) {
        	arr[i]= Integer.parseInt(st.nextToken());
        	if(max<arr[i]) {
        		max=arr[i];
        	}	
        }

        int []p = new int[max];
        for(int i=0; i<max; i++) {
        	p[i]=0;
        }
        p[0]=-2;
        int cnt=0;
        for(int i=2; i<=max; i++) {
        	for(int j=1; j<max; j++) {
            	if((j+1)%i==0) {
            		p[j]--;
            	}
        	}
        }
        for(int i=0; i<n; i++) {
        	if(p[arr[i]-1]==-1) {

                //System.out.println(arr[i]);
        		cnt++;
        	}
        }
        System.out.print(cnt);
    }    
}