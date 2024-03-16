class Solution {
    public int[] solution(int[] numbers) {
        int[] answer;
        int[] Counting_Sort = new int[201];
        int cnt =0;
        for(int i=0; i<numbers.length; i++){
            for(int j= i+1; j<numbers.length; j++){
                if(Counting_Sort[numbers[i]+numbers[j]]++==0){
                    cnt++;
                }
            }
        }
        answer = new int[cnt];
        cnt =0;
        for(int i =0; i<201; i++){
            if(Counting_Sort[i]>0){
                answer[cnt++]=i;
            }
        }
        return answer;
    }
}