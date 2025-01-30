import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int movieNum = 666;
        int cnt = 1;
        while(cnt<N){
            movieNum++;
            if(Integer.toString(movieNum).contains("666")){
                cnt++;
            }
        }
        System.out.print(movieNum);
    }
}
