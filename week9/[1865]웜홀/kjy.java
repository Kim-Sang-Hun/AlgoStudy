import java.io.*;
import java.util.*;

/*
  벨먼-포드 알고리즘
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, w;
    static List<String> num = new ArrayList<>();
    static class Edge{
        int v, w, cost;

        Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }
    static List<Edge> graph;
    static int[] dist;

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        Arrays.fill(dist, (int)1e9);
        graph = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.add(new Edge(s, e, t));
            graph.add(new Edge(e, s, t));
        }
        for (int i = 0; i < w; ++i) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.add(new Edge(s, e, -t));
        }
    }

    static boolean bellmanford() {
        Arrays.fill(dist, (int)1e9);
        dist[1] = 0;
        for (int i = 1; i < n; ++i) {
            for (Edge edge : graph) {
                if(dist[edge.w] <= dist[edge.v] + edge.cost) continue;
                dist[edge.w] = dist[edge.v] + edge.cost;
            }
        }

        for (Edge edge : graph) {
            if(dist[edge.w] > dist[edge.v] + edge.cost){
                return true;
            }
        }
        return false;
    }

    static void solution() {
        if (bellmanford()) sb.append("YES\n");
        else sb.append("NO\n");
    }

    public static void main(String[] args) throws IOException{
        int t = Integer.parseInt(br.readLine());
        for(int i = 1;i <= t; ++i) {
            input();
            solution();
        }
        System.out.println(sb);
    }
}
