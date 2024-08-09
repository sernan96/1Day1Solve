    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt( br.readLine());
            int [][] apart = new int[15][15]; //apart 주민 어디에 몇명사는지 저장
            for(int i =1; i<=15; i++){
                apart[0][i-1]=i; // 1층에 수만큼 넣어주기
            }
            for(int i =0; i<15; i++){
                apart[i][0]=1; // 1호는 전부 1명씩 삼
            }
            for(int i=1; i<15; i++){
                for(int j=1; j<15; j++){
                    apart[i][j]= apart[i-1][j]+apart[i][j-1];
                }
            }
            StringBuilder sb =new StringBuilder();
            while (N--!=0){
                int k = Integer.parseInt(br.readLine());
                int n = Integer.parseInt(br.readLine())-1;
                sb.append(apart[k][n]+"\n");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.print(sb);
        }
    }