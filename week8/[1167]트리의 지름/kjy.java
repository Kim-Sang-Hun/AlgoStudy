import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, answer = 0, node = 1;
    static boolean[] vis;

    static class Edge {
        int v, cost;

        Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static List<Edge>[] edge;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        edge = new ArrayList[n + 1];
        vis = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            edge[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            while(true){
                int nxt = Integer.parseInt(st.nextToken());
                if(nxt == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                edge[idx].add(new Edge(nxt, cost));
            }
        }
    }

    static void dfs(int start, int sum){
        if (answer < sum) {
            node = start;
            answer = sum;
        }
        vis[start] = true;
        for (Edge nxt : edge[start]) {
            if(vis[nxt.v]) continue;
            dfs(nxt.v, sum + nxt.cost);
        }
    }

    static void solution(){
        //사실 아무 정점이나 상관없고, 간선 하나있는 친구 잡아서 시작하는 게 이상적일 수도 있음
        vis = new boolean[n + 1];
        dfs(1, 0);

        //시작 정점으로부터 가장 먼 정점을 찾아서, 얘가 가장 멀리 갈 수 있는 놈이니까 얘 기준으로 다른 모든 노드를 탐색한다
        vis = new boolean[n + 1];
        dfs(node, 0);

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
