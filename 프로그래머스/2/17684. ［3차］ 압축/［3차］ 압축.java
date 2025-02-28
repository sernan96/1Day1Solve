import java.util.*;
class Solution {
    static ArrayList<String> dic;
    static ArrayList<Integer> result;
    
    public int[] solution(String msg) {
        //긴 순서로 뽑아내다가 
        dic = new ArrayList<>();
        result = new ArrayList<>();
        for(int i=0; i<26; i++){//초기화
            char x = 'A';
            x +=i;
            dic.add(String.valueOf(x));
        }
        for(int i=0; i<msg.length(); i++){
            PriorityQueue<String> pq =new PriorityQueue<>(new Comparator<String>(){
                @Override
                public int compare(String o1, String o2){
                    if(o1.length()==o2.length()){
                        return o1.compareTo(o2);
                    }
                    return o2.length()-o1.length();
                }
            });
            for(String x: dic){//앞글자가 같은 단어 전부 넣기
                if(x.charAt(0)==msg.charAt(i)){
                    pq.add(x);
                }
            }
            while(!pq.isEmpty()){
                String cur = pq.poll();
                if(i+cur.length()>msg.length()){
                    continue;
                }
                if(msg.substring(i, i+cur.length()).equals(cur)){
                    System.out.println(msg.substring(i, i+cur.length()));
                    i+=cur.length();
                    result.add(dic.indexOf(cur));
                    if(i!=msg.length()){
                        String temp = cur+""+msg.charAt(i);
                        dic.add(temp);
                        System.out.println("추가 "+temp);
                    }
                    i--;
                    break;
                }
            }
        }
        
        int [] answer = new int[result.size()];
        for(int j=0; j<result.size(); j++){
            answer[j]=result.get(j)+1;                
        }
        return answer;
    }
}
// 처음에 Arraylist에 저장
// 순번:단어 쌍으로 하여 제일 앞글자가 현재 찾고자 하는 단어의 알파벳과 동잏하다면
//우선순위큐(문자열 긴순서)에 저장-> 할때마다 우선순위큐에 넣어줌 (ArrayList에 있는 단어 토대로)