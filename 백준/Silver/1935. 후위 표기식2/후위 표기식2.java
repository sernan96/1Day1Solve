import java.io.*;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Double> st = new Stack<>();//곱 나눗셈
        int n = Integer.parseInt(br.readLine());
        // 입력 값 저장
        char []c = br.readLine().toCharArray();
        double[] arr = new double[n];
        double total=0;
        for(int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }
        for(char x: c) {
        	if(x>='A'&&x<='Z') {
        		st.push(arr[x-'A']);
        	}
        	else {
        		if(!st.empty()){

            		double f=st.pop();
            		double l=st.pop();
        			switch(x) {      			
        				case '*':{
        					st.push(l*f);
        					continue;
        				}
        				case '/':{
        					st.push(l/f);
        					continue;
        				}
        				case '+':{
        					st.push(l+f);
        					continue;
        				}
        				case '-':{
        					st.push(l-f);
        					continue;
						}
        			}
        		}
        	}
        }
        
        System.out.printf("%.2f", st.pop());

    }
}