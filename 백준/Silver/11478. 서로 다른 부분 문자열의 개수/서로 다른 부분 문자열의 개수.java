import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input= br.readLine();
        Set<String> str = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=input.length(); i++){//문자열의 길이
            for(int j =0; j<=input.length()-i; j++){
                str.add(input.substring(j, j+i));
            }
        }
        System.out.print(str.size());
    }
}