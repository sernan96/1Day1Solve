import java.util.*;

class Solution {
    static ArrayList<String> toFind;
    public int[] solution(String[] info, String[] query) {
        HashSet<String> toSum = new HashSet<>();
        HashMap<String, int[]> scores = new HashMap<>();
        for(String x: info){//counting 해주기
            StringTokenizer st = new StringTokenizer(x);
            String condition ="";
            for(int i=0; i<4; i++){
                condition = condition+""+st.nextToken().charAt(0);
            }
            int score =Integer.parseInt(st.nextToken());
            if(!scores.containsKey(condition)){
                scores.put(condition, new int[100001]);
            }
            scores.get(condition)[score]++;
            toSum.add(condition);
        }
        //누적합 해주기
        for(String target : toSum){
            int [] temp = scores.get(target);
            for(int i=99999; i>=0; i--){
                temp[i]+=temp[i+1];
            }
            scores.put(target, temp.clone());
        }
        int[] answer = new int[query.length];
        for(int i=0; i<query.length; i++){
            String q = query[i];
            String find = "";
            toFind = new ArrayList<>(); //검사할 키값들 생성해서 저장
            String[] tokens = q.split(" ");
            for(int j=0; j<7; j++){
                if(tokens[j].equals("and")){
                    continue;
                }
                find = find+String.valueOf(tokens[j].charAt(0));
            }
            DFS(0, find, "");
            int result = 0;
            int standard = Integer.parseInt(tokens[7]);
            for(String target:toFind){
                if(scores.containsKey(target)){
                    result+= scores.get(target)[standard];
                }
            }
            answer[i] = result;
        }
        
        return answer;
    }
    static void DFS(int depth, String find, String make){
        if(depth==4){
            toFind.add(make);
            return;
        }
        if(depth==0&&find.charAt(0)=='-'){
            DFS(depth+1, find, make+"c");
            DFS(depth+1, find, make+"j");
            DFS(depth+1, find, make+"p");
        }
        else if(depth==1&&find.charAt(1)=='-'){
            DFS(depth+1, find, make+"b");
            DFS(depth+1, find, make+"f");
        }
        else if(depth==2&&find.charAt(2)=='-'){
            DFS(depth+1, find, make+"j");
            DFS(depth+1, find, make+"s");
        }
        else if(depth==3&&find.charAt(3)=='-'){
            DFS(depth+1, find, make+"c");
            DFS(depth+1, find, make+"p");
        }
        else{
            DFS(depth+1, find, make+String.valueOf(find.charAt(depth)));
        }
        
    }
}
// 각 조건의 앞자리만 따서
// HashMap을 이용해서 key는 jbjp value는 ArrayList에
// 조건에 해당하는 사람의 점수를 add를 해준다 
// 그렇게 했을때 나중에 쿼리를 할때는 조건이 -라면 둘다 조건의 앞글자들을 전부 반영
// ArrayList를 sort하고 indexOf를 통해 X의 점수가 존재하면 그 뒤에 몇개인지+
// 없으면 넣고 size-index +하고 넣은것 삭제
// 근데 이렇게 하면 시간초과가 날것 같음
// countingSort하듯 배열에 저장하고 뒤에서부터 누적합으로 빈도를 저장해준다.
// 그럼 그 점수 index에 저장된 누적합의 값을 구해주면 생성에서 처리가 되었기에 쿼리때는 접근만하면 됨

