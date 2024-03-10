import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];

        // 초기화: 모든 노드의 부모를 자기 자신으로 설정
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 간선 정보를 이용해 Union 연산 수행
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        int componentCount = 0;
        // 각 노드에 대해, 부모가 자기 자신인 경우(즉, 대표 노드인 경우) 카운트
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) {
                componentCount++;
            }
        }

        System.out.print(componentCount);
    }

    // Find 연산: 노드 x의 최상위 부모(즉, 대표 노드)를 찾는다.
    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // Union 연산: 두 노드의 대표 노드를 합친다.
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }
}
