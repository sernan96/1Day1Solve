import java.util.*;
class Solution {
    static 
    public String solution(long n, String[] bans) {
        //알파벳을 26진수로 변경하는건 단순히 뒤에서부터 ^0부터 시작해서 x-'a'해준값을 각 자리수로 하고
        //10진수로 변환하려면 더해주면 된다.
        //역으로 변환하는 것은 while문으로 해도되고 아니면 재귀로 해도됨
        //우선 ans를 전부 숫자로 변환하며 count해주겠다. -> n에 더해줘야함 
        int cnt =0;
        Arrays.sort(bans, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                if(o1.length()==o2.length()){
                    return o1.compareTo(o2);
                }
                return o1.length()-o2.length();
            }
        });
        for(String x: bans){
            long num = 0;
            for(int i=x.length()-1; i>=0; i--){
                num+=(x.charAt(i)-'a'+1)*(Math.pow(26, x.length()-1-i));
            }        
            if(num<=n+cnt){
                cnt++;
            }
        }
        n+=cnt;
        //이제 10진수를 역으로 26진수로 바꾸며 주문으로 나타내기
        StringBuilder sb = new StringBuilder();
        while(n!=0){
            long add = n%26;
            n/=26;
            char c = '`';
            c+=add;
            if(sb.length()!=0&&sb.charAt(sb.length()-1)=='z'){
                c--;
            }
            sb.append(c=='`'?'z':c);
        }
        return sb.reverse().toString();
    }
}
//10 -> 2진수로 0 1 0 1  5 2 1 0
//방법 1 -> 전부 브루트포스로 만들고 우선순위큐에 넣고 빼주면서 카운트
// PriorityQueue에 전부 넣고 target이 나올때까지 1씩 세어줌
// 그러나 지울만한 주문에 있어선 세어주지 않음(bans 주문들은 HashSet에 넣어주어 빠르게 탐색)
// n의 개수가 10^15로 매우크기 때문에 했다간 시간초과
//방법 2 -> 알파벳 개수를 활용해서 규칙 찾고 그 수보다 작은 bans주문만큼 순번을 더해줘서 찾기 그냥 단순 26진수임
// 단순 계산으로 찾을 수 있기때문에 괜찮은듯 그런데 도중에 순번을 미뤄주기에 정렬을 해도 반례 발생 
