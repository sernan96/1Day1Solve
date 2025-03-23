import java.util.*;
class Solution {
    static int [] dc = {10, 20, 30, 40};
    static int N;
    static ArrayList<String> dcRate;
    static class Node{
        int plus, price;
        public Node(int plus, int price){
            this.plus = plus;
            this.price = price;
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        N = emoticons.length;
        dcRate = new ArrayList<>();
        DFS("", 0);
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                if(o1.plus!=o2.plus){
                    return o2.plus-o1.plus;
                }
                return o2.price-o1.price;
            }
        });
        for(String x: dcRate){
            int [] prices = emoticons.clone();
            for(int i=0; i<N; i++){//이번 할인율에 대한 값 반영
                prices[i] = prices[i]*(100-dc[x.charAt(i)-'0'])/100;
            }
            int plusCnt = 0;
            int cost = 0;
            for(int [] user: users){
                int money = 0;//해당 유저가 사용하는 돈
                for(int i=0; i<N; i++){
                    if(user[0]<=dc[x.charAt(i)-'0']){//구매하는 경우
                        money+=prices[i];
                    }
                }
                if(money>=user[1]){
                    plusCnt++;
                    continue;
                }
                cost+= money;
            }
            //System.out.println(plusCnt+", "+cost+" : "+x);
            pq.add(new Node(plusCnt, cost));
        }
        Node cur = pq.poll();
        int[] answer = {cur.plus, cur.price};
        return answer;
    }
    static void DFS(String str, int depth){
        if(depth == N){
            dcRate.add(str);
            return;
        }
        for(int i=0; i<4; i++){
            DFS(str+String.valueOf(i), depth+1);
        }
    }
}
// 이플 서비스 가입자 수가 우선임
// 조합으로 1~40%의 할인율을 정하는 것은 불가능 하나 문제에서 정해진할인율은 총 4개다
// 단순 계산으로 4^7 = 2^14
// 각 비율을 조합으로 만들어
// 조합을 하나씩 순회하면서 해당 할인율이 반영된 가격 배열을 만들어서 검사하고 result로 pq에 저장
// 각 조합에 따른 이모티콘 플러스 가입자와 비용을 저장
// Node로 저장해서 PriorityQueue에 Node로 넣고
// plus원소를 기준으로 정렬하여 높은 순서로 뽑는다.
// 만약 plus의 수가 같다면 매출이 높은 순서로 한다. 