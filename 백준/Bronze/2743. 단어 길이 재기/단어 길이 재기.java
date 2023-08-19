import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char []c=br.readLine().toCharArray();
        int i=0;
        for(char x:c){
            i++;
        }
        System.out.print(i);
    }    
}