import java.util.*;
class Solution {
    static ArrayList<String> dices;
    static int [] count;
    static HashMap<String, ArrayList<int[]>> scores;
    static int N;
    static int [][] diceInfo;
    public int[] solution(int[][] dice) {
        dices = new ArrayList<>();
        scores = new HashMap<>();
        diceInfo =  dice;
        N = dice.length;
        //주사위 나누는것
        dicePermutation("");
        int winCnt =-1;
        int[] answer = new int[N/2];
        String winDice="";
        for(String x: dices){
            int getWin = match(x);
            if(winCnt<getWin){
                winCnt = getWin;
                winDice = x;
            }
        }
        for(int i=0; i<winDice.length(); i++){
            answer[i] = (winDice.charAt(i)-'0') + 1;
        }
        return answer;
    }
    static void dicePermutation(String str){
        if(N/2 == str.length()){
            dices.add(str);
            //여기서 점수 계산하는 DFS 생성
            count = new int[601];
            int []countSum = new int[601];
            makeScore(str, new ArrayList<>(), 0);
            //count 누적합 해주고 clone해서 scores에 저장해주기
            for(int i=1; i<601; i++){
                countSum[i]+=count[i]+countSum[i-1];
            }
            ArrayList<int []> temp = new ArrayList<>();
            temp.add(count.clone());
            temp.add(countSum.clone());
            scores.put(str, temp);
            return;
        }
        for(int i=0; i<N; i++){
            if(str.length()==0||str.charAt(str.length()-1)-'0'<i){
                dicePermutation(str+""+i);
            }
        }
    }
    static void makeScore(String str, ArrayList<Integer> num, int depth){
        if(depth==str.length()){
            int score = 0;
            for(int x: num){
                score+=x;
            }
            count[score]++;
            return;
        }
        for(int i=0; i<6; i++){
            num.add(diceInfo[str.charAt(depth)-'0'][i]);
            makeScore(str, num, depth+1);
            num.remove(num.size()-1);
        }
    }
    static int match(String A){//이긴 횟수 반환
        String B="";
        for(int i=0; i<N; i++){
            if(!A.contains(i+"")){
                B = B+""+i;
            }
        }
        int winCnt =0;
        for(int i=600; i>0; i--){
            int appear = scores.get(A).get(0)[i];//빈도
            int smallNum = scores.get(B).get(1)[i-1];//작은거 수
            if(appear>0){
                winCnt += appear*smallNum;
                //System.out.print("\n"+i+" "+winCnt+" A: "+A+" B: "+B);
            }
        }
        return winCnt;
        //자기 보다 작은 수가 나올 경우의 수 X 자기자신의 수가 나올 빈도 
    }
}

// 순서: 주사위 분배 String타입으로 붙여서(O)-> 분배된 주사위에 대한 점수 DFS
//-> 점수는 카운팅소트로 점수저장(O) -> 누적합 적용해서 map에 저장 (String, int[] 쌍) (O)
//-> 만든 정보들을 바탕으로 승리 횟수 구하기 
//-> 주사위 하나씩 선정해서 n이 4이면 String 1234에서 A가 고른 주사위를 replaceAll로 ""만들어 B 주사위정해주고 처리
// 브루트포스로 하면 n=10일때 6^5 X 6^5 충분하다
// A가 들고갈 주사위 조합을 우선 ArrayList로 전달하고
// 전달받은 주사위조합에서 발생할 수 있는 점수를 모두 count 배열에 +1
// 그리고 앞에서 부터 누적합으로 저장해준다. 
// 왜냐면 계속 앞에 몇개를 이기는지 하기보다 크면 이기는 거니까 
// 그럼 이기는 경우의 수는 A의 점수의 최대에서 -1한 INDEX의 COUNT배열 값을 더해주면 된다.
// B의 조합은 사실 A가 한걸 저장하고 있다면 따로 해줄 필요없고
// 저장된 값을 불러오면 되어서 A,B나누지 않고 전체에서 경우에 따라 저장을 해준다음
// map에 저장해준다. 