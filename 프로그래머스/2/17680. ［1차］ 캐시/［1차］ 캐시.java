import java.util.*;
class Solution {
    static class Node{
        String city;
        int order;
        public Node(String city, int order){
            this.city = city;
            this.order = order;
        }
    }
    public int solution(int cacheSize, String[] cities) {
        ArrayDeque<String> cache = new ArrayDeque<>();
        HashMap<String, Integer> map = new HashMap<>();
        int answer = 0;
        for(int i=0; i<cities.length; i++){
            String x =cities[i].toUpperCase();
            if(cache.contains(x)){
                answer++;
            }
            else{//비어있거나 없을때
                answer+=5;
                if(cache.size()<cacheSize){//그냥 넣으면 됨
                    cache.addLast(x);
                }
                else if(!cache.isEmpty()){//가득찼다
                    PriorityQueue<Node> pq = new PriorityQueue<Node>(Comparator.comparingInt(o1->o1.order));
                    for(String target: cache){
                        pq.add(new Node(target, map.get(target)));
                    }
                    Node cur = pq.poll();
                    cache.remove(cur.city);
                    cache.addLast(x);
                }
            }
            map.put(x, i);
        }
        return answer;
    }
}
// 큐로 캐시 구현
// 만약 새로운 도시이름이 존재한다면 continue하면서 +1
// 없으면 +5 큐가 다찼다면 poll 해두고 새로 넣어주는 동작까지
// 대소문자 구분 않음
// 그런데 반례발생
// poll할때는 단순히 앞에걸 빼주는 것이 아닌 최근에 사용이 되질않은 도시로 빼줘야함
// HashMap으로 해당 city의 제일 최근순번 기록해주기
// 우선순위큐에 city랑 최근 순번 Node 넣어주고 
// 최근순번 작은 순으로 빼주고 그걸로 삭제해주면 된다.