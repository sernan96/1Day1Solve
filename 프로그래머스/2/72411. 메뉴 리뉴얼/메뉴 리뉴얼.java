import java.util.*;
class Solution {
    static ArrayList<String> combi; 
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        for(int c: course){
            ArrayList<PriorityQueue<String>> pqArr = new ArrayList<>();
            for(int i=0; i<=20; i++){
                pqArr.add(new PriorityQueue<String>());//주문 횟수에 따라 저장해줄것
            }
            HashMap<String, Integer> map = new HashMap<>();
            for(String xOrigin: orders){
                char []x = naturalOrder(xOrigin);
                combi = new ArrayList<>();
                // 단지 서브스트링 할게 아니라 조합을 해야함
                xCombi(x, new ArrayList<>(), c);
                for(String temp: combi){
                    if(map.containsKey(temp)){
                        map.put(temp, map.get(temp)+1);
                    }
                    else{
                        map.put(temp, 1);
                    }
                }
            }
            Set<String> keys = map.keySet();
            int max = 2;
            for(String x: keys){
                int xCnt = map.get(x);
                max = Math.max(max, xCnt);
                pqArr.get(xCnt).add(x);
            }
            PriorityQueue<String> tempPQ = pqArr.get(max);
            while(!tempPQ.isEmpty()){
                answer.add(tempPQ.poll());
            }
        }
        String [] result = new String[answer.size()];
        for(int i=0; i<result.length; i++){
            result [i] = answer.get(i);
        }
        Arrays.sort(result);
        return result;
    }
    static char[] naturalOrder(String x){
        char [] arr = x.toCharArray();
        Arrays.sort(arr);
        return arr;
    }
    static void xCombi(char [] x, ArrayList<Integer> arr, int len){
        if(len==arr.size()){
            StringBuilder sb = new StringBuilder();
            for(int index: arr){
                sb.append(x[index]);
            }
            combi.add(sb.toString());
            return;
        }
        int start = arr.isEmpty()?0: arr.get(arr.size()-1)+1;
        for(int i =start; i<x.length; i++){
            arr.add(i);
            xCombi(x, arr, len);
            arr.remove(arr.size()-1);
        }
    }
}
// 코스의 각 원소 만큼 기존 주문들의 알파벳들을 
// 잘라내어 정렬하여 저장해줌
// HashMap으로 개수를 새어주고
// 개수가 같은 key들끼리 묶어줌 (ArrayList<PQ>로) 
// 코스를 원소순회를 하며 진행해줌