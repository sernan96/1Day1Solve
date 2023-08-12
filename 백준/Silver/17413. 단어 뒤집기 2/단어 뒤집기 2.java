import java.io.*;

public class Main {
	public static void main(String []args) throws IOException {
		StringBuffer sb = new StringBuffer();
		StringBuffer temp = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean change = true;
		String s =  br.readLine();
		StringBuffer ss = new StringBuffer(s);
		ss.append('*');//끝나는 문자
		String s1 = ss.toString();
		char[] c =  s1.toCharArray();
		for(char x: c) {
			
			if(x=='*') {
				if(change==true) {
					sb.append(temp.reverse());
				}
				else {
					sb.append(temp);	
				}
				sb.append(' ');
				temp.setLength(0);
			}
			else if(x=='<'){//<입력
				if(change==true) {
					sb.append(temp.reverse());
				}
				else {
					sb.append(temp);	
				}
				sb.append('<');
				temp.setLength(0);
				change = false;
			}
			else if(x==' ') {
				if(change==true) {
					sb.append(temp.reverse());
				}
				else {
					sb.append(temp);	
				}
				sb.append(' ');
				temp.setLength(0);
			}
			else if(x=='>'){//<입력
				sb.append(temp);
				sb.append('>');
				temp.setLength(0);
				change = true;
			}
			else {
				temp.append(x);
			}
		}
		
		
		System.out.println(sb);
	}
}