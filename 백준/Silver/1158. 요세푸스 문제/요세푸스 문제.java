import java.util.Scanner;
public class Main {
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();	
		sc.close();
		int []circle = new int[n];
		int []dq = new int[n];

		for(int i=1; i<=n; i++) {//덱 선언
			dq[i-1]= i;
		}
		int cnt = 1;//k번마다 삭제해줘야 하므로
		int size = n;
		int index = 0;
		int circle_index = 0;
		while(size!=0) {//요세푸스 순열 입력
			if(n == index) {//마지막일때  처음인덱스로
				index=0;
			}
			if(dq[index]!=0) {
				if(cnt%k==0) {
					circle[circle_index]=dq[index];
					dq[index]=0;
					size--;
					circle_index++;
				}
				cnt++;
				index++;
			}
			else{		
				index++;
			}
		}
		System.out.print("<");
		for(int i=1; i<=n; i++) {
			System.out.print(circle[i-1]);
			if(i!=n) {
				System.out.print(", ");
			}
		}
		System.out.print(">");
	}
}
