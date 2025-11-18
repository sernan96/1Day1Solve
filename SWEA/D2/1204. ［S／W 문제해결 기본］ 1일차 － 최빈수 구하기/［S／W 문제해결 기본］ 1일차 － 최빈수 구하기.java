import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T--!=0){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int [] cnt = new int [101];
            while (st.hasMoreTokens()){
                cnt[Integer.parseInt(st.nextToken())]++;
            }
            int max = 0;
            int result = 0;
            for(int i=0; i<=100; i++){
                if(cnt[i]>=max){
                    max = cnt[i];
                    result = i;
                }
            }
            sb.append("#"+n+" "+result+"\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}
/*
5^10
125 125 625
자신을 제외한 교체를 진행하는 모든 경우의 999999
*/
