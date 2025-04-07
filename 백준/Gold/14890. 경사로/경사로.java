
import java.io.*;
import java.util.*;

class Main{
	static int [][] map, repeatVer, repeatHor;
    public static void main(String [] args)throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int L = Integer.parseInt(st.nextToken());
    	map = new int [N][N];
    	repeatVer = new int [N][N];
    	repeatHor = new int [N][N];
    	for(int i=0; i<N; i++) {
    		StringTokenizer input = new StringTokenizer(br.readLine());
    		for(int j=0; j<N; j++) {
        		map[i][j] = Integer.parseInt(input.nextToken());
        	}
    	}
    	//수평
    	for(int i=0; i<N; i++) {
    		for(int j=1; j<N; j++) {
        		if(map[i][j]==map[i][j-1]) {
        			repeatHor[i][j]=repeatHor[i][j-1]+1;
        		}
        		if(j+1>=N||map[i][j]!= map[i][j+1]) {//더이상 같지 않을때 시작부분에 최대 연속된 횟수 넣어주기
        			repeatHor[i][j-repeatHor[i][j]]=repeatHor[i][j];
        		}
        	}
    	}
    	//수직
    	for(int i=0; i<N; i++) {
    		for(int j=1; j<N; j++) {
        		if(map[j][i]==map[j-1][i]) {
        			repeatVer[j][i]=repeatVer[j-1][i]+1;
        		}
        		if(j+1==N|| map[j][i]!= map[j+1][i]) {//더이상 같지 않을때 시작부분에 최대 연속된 횟수 넣어주기
        			repeatVer[j-repeatVer[j][i]][i]=repeatVer[j][i];
        		}
        	}
    	}
    	int result = 0;
    	//수평부터 검사
    	for(int i=0; i<N; i++) {
    		for(int j=1; j<N; j++) {
    			if(Math.abs(map[i][j]-map[i][j-1])>=2) {
    				break;
    			}
        		if(map[i][j]>map[i][j-1]&&repeatHor[i][j-1]<L-1) { // 앞에 경사로를 설치해야하는 상황(근데 불가능한 경우)
        			break;
        		}
        		else if(map[i][j]<map[i][j-1]) {// 지금 위치에 경사로를 설치해야 하는 상황(근데 불가능한 경우)
        			if(repeatHor[i][j]<L-1) {
            			break;	
        			}
        			else {// 설치 성공시 뒷부분에서 L만큼 빼주기
        				repeatHor[i][j+repeatHor[i][j]]-=L;
        			}
        		}
        		if(j==N-1) {
        			result ++;
        		}
        	}
    	}
    	for(int i=0; i<N; i++) {
    		for(int j=1; j<N; j++) {
    			if(Math.abs(map[j][i]-map[j-1][i])>=2) {
    				break;
    			}
        		if(map[j][i]>map[j-1][i]&&repeatVer[j-1][i]<L-1) { // 위에 경사로를 설치해야하는 상황(근데 불가능한 경우)
        			break;
        		}
        		else if(map[j][i]<map[j-1][i]) {// 지금 위치에 경사로를 설치해야 하는 상황(근데 불가능한 경우)
        			if(repeatVer[j][i]<L-1) {
            			break;	
        			}
        			else {// 설치 성공시 뒷부분에서 L만큼 빼주기
        				repeatVer[j+repeatVer[j][i]][i]-=L;
        			}
        		}
        		if(j==N-1) {
        			result ++;
        		}
        	}
    	}
    	System.out.print(result);
    }
}