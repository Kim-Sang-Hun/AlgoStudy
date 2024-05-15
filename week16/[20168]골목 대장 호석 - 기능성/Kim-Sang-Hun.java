import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
양방향 간선을 저장한다.
dfs돌리면 쉽게 해결된다.
현재 수치심과 간선의 가중치를 비교하며 가고
도착지에 도착했을 때 현재 수치심과 최소 수치심을 비교한다.
메모리 18600kb, 시간 184kb
 */
public class JUN20168_골목대장호석_기능성 {
    static int n, m, a, b, c, min = Integer.MAX_VALUE;
    static List<List<Edge>> edges;
    public static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.get(n1).add(new Edge(n2, weight));
            edges.get(n2).add(new Edge(n1, weight));
        }
        dfs(a, b, c, new boolean[n + 1], 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void dfs(int cur, int target, int money, boolean[] visited, int humil) {
        if (cur == target) {
            min = Math.min(min, humil);
            return;
        }
        visited[cur] = true;
        List<Edge> next = edges.get(cur);
        for (Edge e : next) {
            if (visited[e.to] || money < e.weight) continue;
            visited[e.to] = true;
            dfs(e.to, target, money - e.weight, visited, Math.max(humil, e.weight));
            visited[e.to] = false;
        }
    }
}
