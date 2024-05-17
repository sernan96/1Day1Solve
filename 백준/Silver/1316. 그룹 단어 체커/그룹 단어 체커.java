import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt=0;
        while (N--!=0){
            Set<Character> set = new HashSet<>();
            boolean groupWord=true;
            char temp= ' ';
            for(char x: br.readLine().toCharArray()){
                if(set.contains(x)&&temp!=x){
                    groupWord=false;
                    break;
                }
                else{
                    set.add(x);
                }
                temp=x;
            }
            if(groupWord){
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}