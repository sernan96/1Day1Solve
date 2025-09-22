import java.io.*;
import java.util.*;

public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();
        if(N+K==2){
            System.out.print(0);
            return;
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for(char x: num.toCharArray()){
            q.add(x-'0');
        }
        ArrayDeque<Integer> result = new ArrayDeque<>();
        while (K!=0&&!q.isEmpty()){
            int cur = q.poll();
            if(q.isEmpty()){
                result.add(cur);
                break;
            }
            if(cur>=q.peekFirst()){
                result.add(cur);
            }
            else {
                K--;
                while(K!=0&&!result.isEmpty()&&result.peekLast()<q.peekFirst()){
                    result.pollLast();
                    K--;
                }
            }
        }
        while (!q.isEmpty()){
            result.add(q.poll());
        }
        for(int i=0; i<K; i++){
            result.pollLast();
        }
        for(int x:result){
            System.out.print(x);
        }
    }
}
/*
3412
3234
4321
775841
큐에 넣고 앞에서 부터 하나씩 받으면서
만약 poll한 값이 peekFirst보다 클때까지 stack에서 다시 뽑고 크거나 같다면 stack에 저장
그런데  4321과 같은 수가 나온다면
어차피 왼큰수이므로 뒤에 남은 자리수만큼 잘라내고 출력
*/