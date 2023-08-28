import java.io.*;
import java.util.Scanner;
public class Main{
    public static void main(String[] args)throws IOException{
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int []a = new int[3];
        for(int i=0; i<3;i++) {
        	a[i]=sc.nextInt();
        }
        System.out.println((a[0]+a[1])%a[2]);
        System.out.println(((a[0]%a[2]) + (a[1]%a[2]))%a[2]);
        System.out.println((a[0]*a[1])%a[2]);
        System.out.println(((a[0]%a[2]) * (a[1]%a[2]))%a[2]);
    }    
}