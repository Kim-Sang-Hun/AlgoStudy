import java.util.*;
import java.io.*;

public class Main {

    private static Map<Integer, List<Integer>> tree = new HashMap<Integer, List<Integer>>();
    private static Edge[] edges;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        edges = new Edge[N+1];

        for(int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!tree.containsKey(a)) tree.put(a, new ArrayList<Integer>());
            if(!tree.containsKey(b)) tree.put(b, new ArrayList<Integer>());

            edges[n] = new Edge(a, b);

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        int Q = Integer.parseInt(br.readLine());

        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(t == 1) {
                sb.append(isCutVertex(k) ? "yes\n" : "no\n");
            }

            if(t == 2) {
                sb.append("yes\n");
            }
        }

        System.out.println(sb);

    }

    private static boolean isCutVertex(int node) {
        return (tree.get(node) != null && tree.get(node).size() > 1);
    }

    static class Edge {
        int node1, node2;
        Edge(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }
    }
    
}
