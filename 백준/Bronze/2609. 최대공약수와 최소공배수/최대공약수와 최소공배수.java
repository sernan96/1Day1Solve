import java.io.*;
import java.util.Scanner;
public class Main{
    public static void main(String[] args)throws IOException{
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int max = 0;
        if(a<b) {//swap
        	int temp=0;
        	temp = a;
        	a=b;
        	b=temp;
        }
        for(int i=b; i>=0; i--) {
        	if(a%i==0 && b%i==0) {//최대공약수
        		max=i;
        		break;
        	}
        }
        int min=1;
        while((min*b)%a!=0) {
        	
        	min++;
        }
        System.out.println(max);

        System.out.println(min*b);

    }    
}