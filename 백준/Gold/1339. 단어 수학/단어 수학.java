import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> cnt = new HashMap<>();
        ArrayList<String> arr = new ArrayList<>();

        for(int i=0; i<N; i++){
            arr.add(br.readLine());
        }
        for (int i=0; i<N; i++){
            String input = arr.get(i);
            for(int j = 0; j<input.length(); j++){
                char c = input.charAt(j);
                if(cnt.containsKey(c)){
                    cnt.put(c, cnt.get(c)+(int) Math.pow(10, input.length()-1-j));
                }
                else {
                    cnt.put(c, (int) Math.pow(10, input.length()-1-j));
                }
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int x: cnt.values()){
            pq.add(x);
        }
        int num = 9;
        long sum =0;
        while (!pq.isEmpty()){
            int x = pq.poll();
            sum+=(long) x*(num--);
        }
        System.out.print(sum);
    }
}
/*
일단 자리수가 많고 높은 자리부터 큰 수를 지정해준다.
그렇게 하기 위해서 각 자리수에 해당하는 알파벳을
자리 별로 저장해주고 등장한 횟수가 클수록 만약 같은 자리수에 번호를 부여한다고 했을때
더 유리하므로 우선순위큐 10개 배열(1~9)을 만들어준다.
정렬은 Map으로 저장한 횟수의 key값으로 해준다.
그 다음 순차적으로 9~1까지 번호를 배정해주고
정답을 출력해줌
*/