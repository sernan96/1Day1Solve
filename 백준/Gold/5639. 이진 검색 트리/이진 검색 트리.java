import java.io.*;
import java.util.*;

public class Main {
    static class Node{
         public Node left, right;
         int value;
         Node(int value){
             this.value = value;
         }

    }
    static StringBuilder sb = new StringBuilder();//출력해줄 버퍼
    static Node head;
    public static void main(String[] args) throws IOException {
        init();
        postorder(head);
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Boolean first = true;
        String input = "";

        while((input = br.readLine()) != null) {
            if(input.equals("")){
                return;
            }
            int x = Integer.parseInt(input);
            if (first) {// root 노드 만들어주기
                head = new Node(x);
                first=false;
            } else {// 넣어주기
                put(x, head);
            }
        }
    }
    static void put(int x, Node parent){
        if(parent.value>x){
            if(parent.left==null){
                parent.left = new Node(x);
                return;
            }
            put(x, parent.left);
        }
        else{
            if(parent.right==null){
                parent.right = new Node(x);
                return;
            }
            put(x, parent.right);
        }
    }
    static void postorder(Node find){
        if(find==null){
            return;
        }
        postorder(find.left);
        postorder(find.right);
        sb.append(find.value+"\n");
    }
}
