import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        int cnt = 0;
        while (st.hasMoreTokens()){
            arr.add(Integer.parseInt(st.nextToken()));
        }
        if(N==1){
            System.out.print(cnt);
            return;
        }
        for(int i=1; i<N; i++){
            int front =arr.get(i-1);
            int back =arr.get(i);
            if(front>back){// 7 3
                while (back<front){
                    back*=2;
                    cnt++;
                }
                arr.set(i, back);
            }
        }
        System.out.print(cnt);
    }
}