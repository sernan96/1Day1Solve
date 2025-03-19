import java.util.*; 
class Solution {
    static class Node{
        int carNum, fee;
        public Node(int carNum, int fee){
            this.carNum = carNum;
            this.fee = fee;
        }
    }
    public int[] solution(int[] fees, String[] records) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1.carNum));
        HashMap<Integer,Integer> sumTime = new HashMap<>();
        HashMap<Integer,Integer> lastCome = new HashMap<>();
        for(String x: records){
            StringTokenizer st = new StringTokenizer(x);
            String temp = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            String state = st.nextToken();
            int time = ((temp.charAt(0)-'0')*10+(temp.charAt(1)-'0'))*60+((temp.charAt(3)-'0')*10+(temp.charAt(4)-'0'));
            if(state.equals("IN")){//입차
                lastCome.put(num, time);
            }
            else{
                //누적시간 최신화
                int pre =0;
                if(sumTime.containsKey(num)){
                    pre = sumTime.get(num);
                }
                sumTime.put(num, pre+time-lastCome.get(num));
                lastCome.remove(num);//다끝나고 출차기록 없는 차량들 처리해줘야함
            }
        }
        Set<Integer> remain = lastCome.keySet();
        for(int car: remain){
            int pre =0;
            if(sumTime.containsKey(car)){
                pre = sumTime.get(car);
            }
            sumTime.put(car, pre+23*60+59-lastCome.get(car));
        }
        //누적시간 기반으로 계산
        Set<Integer> carSet = sumTime.keySet();
        for(int car: carSet){
            int cost = fees[1];
            if(sumTime.get(car)>fees[0]){//기본요금보다 많이 나왔을때
                cost+=(sumTime.get(car)-fees[0])/fees[2]*fees[3];
                if((sumTime.get(car)-fees[0])%fees[2]!=0){//나머지가 있으면 요금 추가
                    cost+=fees[3];
                }
            }
            pq.add(new Node(car, cost));
        }
        int[] answer = new int[pq.size()];

        int len =pq.size();
        for(int i =0; i<len; i++){
            answer[i] = pq.poll().fee;
        }
        return answer;
    }
}
//누적 주차 시간을 계산해서 요금은 일괄로 정산함
//map으로 차량번호별로 누적시간 계산해주는것 하나, 입차시간 적어둔거 하나 2개로 관리
//그리고 마지막에 계산해주며 차량번호랑 같이 PriorityQueue에 Node로 넣어주고
//차량번호를 기준으로 하나씩 뽑으면서 넣어주면 끝
