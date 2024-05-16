import java.util.*;
import java.io.*;
/*
  Title: 골목 대장 호석 - 기능성
  Tier: Gold 5
  Algorithm: BackTracking
  Constraint: 3 Second, 512MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, a, b, c, answer;
    static class Edge{
        int v, cost;
        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static List<Edge>[] edge;
    static boolean[] vis;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        edge = new ArrayList[n + 1];
        vis = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            edge[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edge[start].add(new Edge(end, cost));
            edge[end].add(new Edge(start, cost));
        }
        answer = (int)1e9;
    }

    static void dfs(int cur, int rest, int maxShame) {
        //b 도착했을 때
        if (cur == b) {
            answer = Math.min(answer, maxShame);
            return;
        }
        for (Edge nxt : edge[cur]) {
            if(rest - nxt.cost < 0) continue;
            if(vis[nxt.v]) continue;
            vis[nxt.v] = true;
            int shame = Math.max(maxShame, nxt.cost);
            dfs(nxt.v, rest - nxt.cost, shame);
            vis[nxt.v] = false;
        }
    }

    static void solution() {
        dfs(a, c, 0);
        if(answer == (int)1e9) answer = -1;
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
