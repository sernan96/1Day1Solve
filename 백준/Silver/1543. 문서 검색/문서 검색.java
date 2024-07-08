import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String pattern = br.readLine();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        int cnt=0;
        while(m.find()){
            cnt++;
        }
        System.out.print(cnt);
    }
}