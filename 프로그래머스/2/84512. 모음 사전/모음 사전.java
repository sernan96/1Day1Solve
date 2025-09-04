import java.util.*;

class Solution {
    static String [] list = {"A", "E", "I", "O", "U"};
    static List<String> dic;
    public int solution(String word) {
        dic = new ArrayList<>();
        DFS("");
        return dic.indexOf(word);
    }
    static void DFS(String str){
        dic.add(str);
        if(str.length()==5){
            return;
        }
        for(String x: list){
            DFS(str+x);
        }
    }
}