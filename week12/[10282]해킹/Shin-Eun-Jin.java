import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine()); // 테스트 케이스 수

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            String[] firstLine = reader.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]); // 컴퓨터 개수
            int d = Integer.parseInt(firstLine[1]); // 의존성 개수
            int c = Integer.parseInt(firstLine[2]); // 해킹당한 컴퓨터 번호

            // 그래프 초기화
            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            // 의존성 입력
            for (int i = 0; i < d; i++) {
                String[] dependency = reader.readLine().split(" ");
                int a = Integer.parseInt(dependency[0]);
                int b = Integer.parseInt(dependency[1]);
                int s = Integer.parseInt(dependency[2]);

                graph.get(b).add(new Edge(a, s));
            }

            // 감염 시간 계산
            int[] dist = dijkstra(n, c, graph);

            // 감염된 컴퓨터 수와 마지막 컴퓨터 감염 시간 계산
            int infectedCount = 0;
            int maxTime = 0;

            for (int i = 1; i <= n; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    infectedCount++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }

            output.append(infectedCount).append(" ").append(maxTime).append("\n");
        }

        System.out.print(output.toString());
    }

    static int[] dijkstra(int n, int start, List<List<Edge>> graph) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // 시작점 초기화
        dist[start] = 0;
        pq.offer(new int[] {start, 0});

        // 다익스트라 알고리즘 수행
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int time = current[1];

            // 현재 노드에서 연결된 노드 탐색
            for (Edge edge : graph.get(node)) {
                int next = edge.to;
                int nextTime = edge.time;

                // 최단 경로 업데이트
                if (time + nextTime < dist[next]) {
                    dist[next] = time + nextTime;
                    pq.offer(new int[] {next, dist[next]});
                }
            }
        }

        return dist;
    }

    static class Edge {
        int to;
        int time;

        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
}
