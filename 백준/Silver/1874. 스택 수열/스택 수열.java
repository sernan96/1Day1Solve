import java.util.Scanner;
import java.util.Stack;
public class Main {
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		Stack<Integer> st = new Stack<>();
		StringBuffer sb = new StringBuffer(); //+,-저장
		int limit =sc.nextInt(), max=0, min=limit, cnt=0;
		for(int i=0; i<limit; i++) {
			int n= sc.nextInt();
			if(n>max) {
				for(int j=max+1; j<=n; j++) {
					
					st.push(j);
					sb.append("+").append("\n");

				}
				sb.append("-").append("\n");
				st.pop();
				max=n;
			}
			else {
				if(st.peek()==n) {
					sb.append("-").append("\n");
					st.pop();
				}
				else {
					System.out.println("NO");
					return;
				}
			}		
			
		}
		sc.close();
		System.out.println(sb);
	}
}
