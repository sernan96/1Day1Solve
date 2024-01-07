import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

import static java.lang.System.exit;
public class Main {

    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<t; i++){
            int k= Integer.parseInt(br.readLine());
            TreeMap <Integer, Integer> q = new TreeMap<>();
            for(int j=0; j<k; j++){
                String[] str = br.readLine().split(" ");
                int n = Integer.parseInt(str[1]);
                if (str[0].equals("I")){//Insert
                    q.put(n, q.getOrDefault(n,0)+1);
                }
                else{// delete
                    if(q.isEmpty()){
                        continue;
                    }
                    if(n==1){//최대값 삭제
                        if(q.get(q.lastKey())==1){
                            q.remove(q.lastKey());
                        }
                        else{
                            q.put(q.lastKey(), q.get(q.lastKey())-1);
                        }
                    }
                    else{// 최소값 삭제
                        if(q.get(q.firstKey())==1){
                            q.remove(q.firstKey());
                        }
                        else{
                            q.put(q.firstKey(), q.get(q.firstKey())-1);
                        }
                    }

                }
            }
            if(q.isEmpty()){
                sb.append("EMPTY\n");
            }
            else{
                sb.append(q.lastKey()+" "+q.firstKey()+"\n");
            }
        }
        System.out.println(sb);
    }

}