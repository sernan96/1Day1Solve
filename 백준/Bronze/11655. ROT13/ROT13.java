import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        char[]c = br.readLine().toCharArray();
        char []b = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char []s = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for(char x:c) {
        	if(x>='A' && x<='Z') { //55,90
        		if(x>='N') {
        			sb.append(b[(x-'A'+13)%26]);
        		}
        		else {
        			sb.append(b[(x-'A'+13)]);
        		}
        	}
        	else if(x>='a' && x<='z') {//97, 122
        		if(x>='n') {
        			sb.append(s[(x-'a'+13)%26]);
        		}
        		else {
        			sb.append(s[(x-'a'+13)]);
        		}
        	}
        	else {
        		sb.append(x);
        	}
        }
        System.out.println(sb);
    }
}
