import java.io.*;
import java.util.*;

public class isayaksh {

    private static int[] parent;
    private static Set<Integer> set = new HashSet<Integer>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int n, m, node1, node2, T;

        for(long t = 1; t < Long.MAX_VALUE; t++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            parent = new int[n+1];
            for(int i = 1; i < n+1; i++) {
                parent[i] = i;
            }

            T = 0;
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                node1 = Integer.parseInt(st.nextToken());
                node2 = Integer.parseInt(st.nextToken());

                union(node1, node2);

            }

            set.clear();
            for(int node = 1; node < n+1; node++) {
                set.add(find(node));
            }

            T = set.size() - (set.contains(0) ? 1 : 0);
            
            if(T == 0) sb.append("Case " + t + ": No trees.\n");
            if(T == 1) sb.append("Case " + t + ": There is one tree.\n");
            if(T > 1) sb.append("Case " + t + ": A forest of " + T + " trees.\n");

        }

        System.out.println(sb);

    }

    private static int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) parent[Math.min(x, y)] = 0;
        else parent[Math.max(x, y)] = Math.min(x, y);
    }

}
