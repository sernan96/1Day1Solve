//일단 한자리수마다 +1씩은 기본 만약 앞자리 숫자가 1이라면 1추가
// 2라면 그 뒷자리가 6이하이면 +1 추가로 해줌

// 앞이 0이거나 뒤가 0인경우에도 그대로 가야함
import java.io.*;

class Main{
    public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String code = br.readLine();
        if(code.charAt(0)=='0'||(code.charAt(code.length()-1)=='0'&&code.charAt(code.length()-2)-'0'>2)){
            System.out.print(0);
            return;
        }
        int [] dp = new int[code.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=code.length(); i++){
            dp[i] = dp[i-1];
            int x = Integer.parseInt(code.charAt(i-2)+""+code.charAt(i-1));
            if(x==0||x%10==0&&x/10>=3){//0이나 30이상
                System.out.print(0);
                return;
            }
            else if(x<=26&&x>=11&&x!=20){
                dp[i]= Math.max(dp[i], dp[i-2]+dp[i-1])%1000000;
            }
            if(x==10||x==20){
                dp[i]=dp[i-2];
            }
        }
        System.out.print(dp[code.length()]);
    }
}