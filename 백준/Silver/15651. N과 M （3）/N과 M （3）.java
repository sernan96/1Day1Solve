import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static StringBuilder sb= new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NM.nextToken());
        M = Integer.parseInt(NM.nextToken());
        makeNM(new StringBuilder(), 0);
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    static void makeNM(StringBuilder temp, int len){
        if(len==M){
            sb.append(temp+"\n");
            return;
        }
        for(int i=1; i<=N ; i++){
            temp.append(i);
            makeNM(new StringBuilder(temp+" "), len+1);
            temp.setLength(temp.length()-1);
        }
    }
}