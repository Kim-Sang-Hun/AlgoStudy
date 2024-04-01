import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 한 노드에서 가장 먼 노드까지 가는 길은 항상 트리의 지름의 일부를 포함한다.
// 따라서 임의의 노드에서 시작하여 가장 먼 노드에 간 후 그 노드에서 가장 먼 노드로 이동하면
// 그것이 바로 트리의 지름이다.
public class Main {

  static List<Edge>[] edges;

  static class Edge {

    int to, weight;

    public Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  static boolean[] visited;
  static int max;
  static int longestIdx;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    visited = new boolean[N];
    edges = new List[N];
    for (int i = 0; i < N; i++) {
      List<Edge> list = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken());
      while (true) {
        int to = Integer.parseInt(st.nextToken()) - 1;
        if (to == -2)
          break;
        int weight = Integer.parseInt(st.nextToken());
        list.add(new Edge(to, weight));
      }
      edges[num-1] = list;
    }
    dfs(0, 0);
    Arrays.fill(visited, false);
    dfs(longestIdx, 0);
    System.out.println(max);
  }

  private static void dfs(int to, int weight) {
    if (weight > max) {
      max = weight;
      longestIdx = to;
    }
    visited[to] = true;
    List<Edge> list = edges[to];
    for (int i = 0; i < list.size(); i++) {
      Edge cur = list.get(i);
      if (visited[cur.to]) continue;
      visited[cur.to] = true;
      dfs(cur.to, cur.weight + weight);
    }

  }
}
