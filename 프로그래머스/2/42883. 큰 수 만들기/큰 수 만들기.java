class Solution {
    public String solution(String number, int k) {
        int start =0, max=-1, end=0;
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<number.length()-k; i++){
            for(int j =start; j<=k+end; j++){
                if(max<(Integer.valueOf(number.charAt(j))-Integer.valueOf('0'))){
                    start = j+1;
                    max=Integer.valueOf(number.charAt(j))-Integer.valueOf('0');
                }
            }
            if(max!=-1){
                sb.append(max);    
                end++;            
                max=-1;
            }
            
        }
        return sb.toString();
    }
}