import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        gop(A,B,C);
    }
    static void gop (long a, int b, int c){
        long result =1;
        if(a>=c){
            a=a%c;
        }
        while(b>0){
            if (b % 2 != 0) {//홀수일때
                result = result*a%c;
            }
            b=b>>1;
            a=a*a%c;
        }
        System.out.print(result);
    }
}
