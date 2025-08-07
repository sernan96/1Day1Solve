import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] students = new int[N];
        int [] count = new int [1000001];
        for(int i=0; i<N; i++){
            students[i] = Integer.parseInt(br.readLine());
            count[students[i]]++;
        }
        for(int i=0; i<N; i++){
            int cnt = 0;
            for(int j=1; j*j<=students[i]; j++){
                if(students[i]%j==0){
                    cnt+=count[j];
                    if(j!=students[i]/j) cnt+=count[students[i]/j];
                }
            }
            System.out.println(Math.max(cnt-1, 0));
        }
    }
}
/*
자신의 좌 우로 이동하면서 나눠떨어지는 수를 세어준다.
근데 사실상 자신을 포함한 존재하는 약수의 수가 몇개 존재하는지를 알면 되는데
에라토스테네스의 체를 써서 배열에 개수를 새놓은 수를 돌면서 조사한다.
근데 이렇게하면 결국 한번에 10^8 1억정도 될거같다.
*/