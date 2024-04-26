import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, Comparator.comparingInt((String s)->s.charAt(0)).thenComparingInt(String :: length));
        HashMap<String, Integer> pb = new HashMap<>();
        for(String p:phone_book){
            pb.put(p, 1);
        }
        for(String p:phone_book){
            for(int i=1; i<p.length(); i++){
                if(pb.containsKey(p.substring(0, i))){
                    return false;
                }
            }
        }
        return true;
    }
}