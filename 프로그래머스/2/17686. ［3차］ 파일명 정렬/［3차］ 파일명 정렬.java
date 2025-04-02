import java.util.*; //1차시도 49:55초 2차 시도
class Solution {
    static class Node{
        int num, order;
        String tail, number, head;
        public Node(String head, String number, String tail, int order){
            this.order = order;
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.num = Integer.parseInt(number);
        }
    }
    static String numbers = "0123456789";
    public String[] solution(String[] files) {
        HashMap<String, PriorityQueue<Node>> map = new HashMap<>();
        PriorityQueue<String> heads = new PriorityQueue<>();
        for(int orders =0; orders<files.length; orders++){
            String x = files[orders];
            int startNum = 100000;
            for(char c : numbers.toCharArray()){
                if(x.indexOf(c)>=0){
                    startNum = Math.min(x.indexOf(c), startNum);
                }
            }
            int endNum = startNum;
            for(int i=startNum; i<x.length(); i++){
                if(numbers.indexOf(x.charAt(i))<0){
                    break;
                }
                endNum++;
            }
            String originHead = x.substring(0, startNum);
            String head = originHead.toLowerCase();
            String n = x.substring(startNum, endNum);
            String tail = endNum == x.length()?"":x.substring(endNum, x.length());
            if(map.containsKey(head)){
                map.get(head).add(new Node(originHead, n,tail, orders));
            }
            else{
                heads.add(head);
                PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing((Node o) -> o.num).thenComparing(o -> o.order));
                pq.add(new Node(originHead, n,tail, orders));
                map.put(head, pq);
            }
        }
        ArrayList<String> answer = new ArrayList<>();
        while(!heads.isEmpty()){
            String nowHead = heads.poll();
            PriorityQueue<Node> temp = map.get(nowHead);
            while(!temp.isEmpty()){
                Node cur = temp.poll();
                answer.add(cur.head+cur.number+cur.tail);
            }
        }
        String [] result = new String[answer.size()];
        for(int i=0; i<answer.size(); i++){
            result[i] = answer.get(i);
        }
        return result;
    }
}
// 1. 헤드 기준으로 사전순 정렬 (stable하게)
// 2. head가 같으면 number 작은 순으로
// 3. 넘버도 같으면 들어온 순서 그대로 
// 일단 헤드가 key number과 tail을 node로 저장한 우선순위큐로 value로 저장한다.
// 그리고 중점은 어떻게 head number tail을 나눌것이냐 tail은 아무것도 없을수도 있음
//  
