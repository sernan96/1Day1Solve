import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] factories = new int [N+2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            factories[i] = Integer.parseInt(st.nextToken());
        }
        int cost = 0;
        for(int i=0; i<N; i++){
            if(factories[i]>0){
                int ramen = factories[i];
                cost+=ramen*3;
                factories[i]-=ramen;

                ramen = Math.min(ramen, factories[i+1]);
                cost+= ramen*2;
                factories[i+1]-=ramen;

                ramen = Math.min(ramen, factories[i+2]-Math.min(factories[i+2], factories[i+1]));
                cost+= ramen*2;
                factories[i+2]-=ramen;
            }
        }
        System.out.print(cost);
    }
}