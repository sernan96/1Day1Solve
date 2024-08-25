import java.io.*;
import java.util.*;

public class Main {
    static int s, t;
    static class pair {
        long num;
        StringBuilder cal;
        public pair(long num, StringBuilder cal){
            this.num= num;
            this.cal = cal;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        s = Integer.parseInt(NM.nextToken());
        t = Integer.parseInt(NM.nextToken());
        if(s==t){
            System.out.print("0");
            return;
        }
        System.out.print(BFS());
    }
    static StringBuilder BFS(){//연산의 아스키 코드 순서는 '*', '+', '-', '/'
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(s, new StringBuilder()));
        Set<Long> visited = new HashSet<>();
        while(!q.isEmpty()){
            pair curS = q.poll();
            long num = curS.num;
            StringBuilder temp = curS.cal;
            if(num==t){
                return temp;
            }
            if(!visited.contains(num)){
                visited.add(num);
                temp.append("*");
                q.add(new pair(num*num, new StringBuilder(temp)));
                temp.deleteCharAt(temp.length()-1);

                temp.append("+");
                q.add(new pair(num*2, new StringBuilder(temp)));
                temp.deleteCharAt(temp.length()-1);

                temp.append("-");
                q.add(new pair(0, new StringBuilder(temp)));
                temp.deleteCharAt(temp.length()-1);

                temp.append("/");
                q.add(new pair(1, new StringBuilder(temp)));
                temp.deleteCharAt(temp.length()-1);
            }
        }
        return new StringBuilder("-1");
    }
}