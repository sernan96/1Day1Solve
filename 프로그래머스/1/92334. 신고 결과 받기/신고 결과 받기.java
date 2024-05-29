import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, Set<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> numMap = new HashMap<>();
        for(String id : id_list){
            reportMap.put(id, new HashSet<String>());//신고한 사람ID Set에 넣기
        }
        for(String str: report){
            String [] st = str.split(" ");
            String reportMan = st[0];
            String reportedMan= st[1];
            if(!reportMap.get(reportMan).contains(reportedMan)){
                if(numMap.get(reportedMan)!=null){
                    numMap.put(reportedMan, numMap.get(reportedMan)+1);
                }
                else{
                    numMap.put(reportedMan, 1);
                }
            }
            reportMap.get(reportMan).add(reportedMan);
        }
        int index =0;
        for(String reportid : id_list){
            int cnt =0;
            for(String reportedid : id_list){
                if(reportMap.get(reportid).contains(reportedid)){
                    if(numMap.get(reportedid)>=k){
                        cnt++;
                    }
                }
            }
            answer[index++] = cnt;
        }
        return answer;
    }
}