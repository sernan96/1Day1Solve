import java.util.Scanner;
import java.util.Stack;
public class Main {
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		sc.nextLine();
		boolean det=true;
		for(int i=0; i<n; i++) {
			Stack<Character> st = new Stack<>();
			String s =sc.nextLine();
			
			det=true;
			for(int j=0; j<s.length(); j++) {
				char temp = s.charAt(j);
				if(temp =='(') {
					st.push('(');
				}
				else {
					if(st.empty()) {
						det=false;
					}
					else{//앞에 (가 바로 있다면 제거
						st.pop();
						//System.out.println("POP~");
					}
				}
			}
			if(det && st.empty()) {
				System.out.println("YES");
			}
			else  {
				System.out.println("NO");
			}
			
		}
		
	}
}