import java.util.*;
import java.io.*;
/*
  Title: 해킹
  Tier: Gold 4
  Algorithm: Dijkstra
  Constraint: 2 Second, 256MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int t, n, d, c, infectedCnt, answer;
    static class Edge {
        int v, cost;
        Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static List<Edge>[] edge;
    static boolean[] vis;
    static int[] infectedTime;
    static final int MAX = 2147483647;

    static void solution() {
        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        q.add(new Edge(c, 0));
        infectedTime[c] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll().v;
            if(vis[cur]) continue;
            vis[cur] = true;
            for (Edge nxt : edge[cur]) {
                if (infectedTime[nxt.v] <= infectedTime[cur] + nxt.cost) continue;
                infectedTime[nxt.v] = infectedTime[cur] + nxt.cost;
                q.add(new Edge(nxt.v, infectedTime[nxt.v]));
            }
        }
        for (int i = 1; i <= n; ++i) {
            if (infectedTime[i] == MAX) continue;
            ++infectedCnt;
            answer = Math.max(answer, infectedTime[i]);
        }
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        infectedCnt = answer = 0;
        infectedTime = new int[n + 1];
        vis = new boolean[n + 1];
        edge = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) {
            infectedTime[i] = MAX;
            edge[i] = new ArrayList<>();
        }
        for (int i = 1; i <= d; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            edge[b].add(new Edge(a, s));
        }
    }

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; ++i) {
            input();
            solution();
            sb.append(infectedCnt).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
