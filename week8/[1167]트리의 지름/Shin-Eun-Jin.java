import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int farthestNode;
    static int maxDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        List<List<Edge>> graph = new ArrayList<>(V + 1);

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                graph.get(from).add(new Edge(to, weight));
            }
        }

        dfs(1, 0, graph, new boolean[V + 1]);
        dfs(farthestNode, 0, graph, new boolean[V + 1]);

        System.out.println(maxDistance);
    }

    static void dfs(int node, int distance, List<List<Edge>> graph, boolean[] visited) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (Edge edge : graph.get(node)) {
            if (!visited[edge.to]) {
                dfs(edge.to, distance + edge.weight, graph, visited);
            }
        }
    }
}
