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
        ArrayDeque<Integer> q = new ArrayDeque<>();//뽑아서 하나씩 확인할 큐
        ArrayDeque<Integer> result = new ArrayDeque<>();//결과로 출력할 스택

        for(char x: num.toCharArray()){
            q.add(x-'0');
        }

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
                while(--K!=0&&!result.isEmpty()&&result.peekLast()<q.peekFirst()){
                    result.pollLast();
                }
            }
        }
        while (!q.isEmpty()){//q에 남은거 result에 넣어주기
            result.add(q.poll());
        }
        for(int i=0; i<K; i++){//K가 0이 안되었는데 끝났다-> 전부 왼큰수가 되었다면 뒤에서부터 남은 K 횟수만큼 pollLast 해줌
            result.pollLast();
        }
        for(int x:result){
            System.out.print(x);
        }
    }
}