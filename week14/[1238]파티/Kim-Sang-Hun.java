import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
다익스트라로 해결할 수 있는 문제이다
모든 정점에서 X로 가는 거리와
X에서 모든 정점으로 가는 거리만 계산해서 최대값을 계산한다.
X에서 모든 정점으로 가는 거리를 계산하기 위해 reverse를 두고
반대로 계산해준다.
 */
public class JUN1238_파티 {

    static int n, m, x;
    static int[] toX, fromX;
    static List<List<Edge>> edges, reverse;

    static class Edge {
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
        x = Integer.parseInt(st.nextToken());
        toX = new int[n + 1];
        fromX = new int[n + 1];
        Arrays.fill(toX, Integer.MAX_VALUE);
        Arrays.fill(fromX, Integer.MAX_VALUE);
        toX[x] = 0;
        fromX[x] = 0;
        edges = new ArrayList<>();
        reverse = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            edges.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.get(from).add(new Edge(to, weight));
            reverse.get(to).add(new Edge(from, weight));
        }
        bfs();
        // 이후 X로 가는 거리와 X에서 오는 거리를 보고 최대값 계산
        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            answer = Math.max(answer, toX[i] + fromX[i]);
        }
        System.out.println(answer);
    }

    private static void bfs() {
        // 먼저 다른 정점에서 X까지 가는 거리 계산
        // X에서 시작해서 reverse에 있는 값으로 계산하면 역산이 가능하므로 이 방식이 좋을 것이다
        PriorityQueue<Edge> qu = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        qu.add(new Edge(x, 0));
        boolean[] visited = new boolean[n + 1];

        while (!qu.isEmpty()) {
            Edge e = qu.poll();
            int cur = e.to;
            if (visited[cur]) continue;
            visited[cur] = true;

            for (Edge edge : reverse.get(cur)) {
                if (!visited[edge.to] && toX[edge.to] > toX[cur] + edge.weight) {
                    toX[edge.to] = toX[cur] + edge.weight;
                    qu.add(new Edge(edge.to, toX[edge.to]));
                }
            }
        }
        // 이후 X에서 다른 정점까지 가는 거리 계산
        Arrays.fill(visited, false);
        qu.add(new Edge(x, 0));

        while (!qu.isEmpty()) {
            Edge e = qu.poll();
            int cur = e.to;
            if (visited[cur]) continue;
            visited[cur] = true;

            for (Edge edge : edges.get(cur)) {
                if (!visited[edge.to] && fromX[edge.to] > fromX[cur] + edge.weight) {
                    fromX[edge.to] = fromX[cur] + edge.weight;
                    qu.add(new Edge(edge.to, fromX[edge.to]));
                }
            }
        }
    }
}
