import java.io.*;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        Stack<Character> st = new Stack<>();
        char []c = br.readLine().toCharArray();
        for(char x: c) {
        	if(x>='A'&&x<='Z') {
        		sb.append(x);
        	}
        	else {
        		switch(x) {
        			case '*':
        			case '/':
        			case '+':
        			case '-':
        				while(!st.empty() &&first(st.peek())>=first(x)) {
        					sb.append(st.pop());
        				}
        				st.push(x);
        				break;
        			case '(':
        				st.push(x);
        				break;
        			case ')':
        				while(!st.empty() &&st.peek()!='(') {
        					sb.append(st.pop());
        				}
        				st.pop();
        				
        		}
        	}
        }
        while (!st.empty()) {
            sb.append(st.pop());
        }
        
        System.out.println(sb);

    }
    public static int first(char pr) {
		if(pr=='('||pr==')') {
			return 0;
		}
		else if(pr=='+'||pr=='-') {
			return 1;
		}
		else {
			return 2;
		}
    }
}