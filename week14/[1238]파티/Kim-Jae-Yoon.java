import java.util.*;
import java.io.*;
/*
  Title: 파티
  Tier: Gold 3
  Algorithm: Dijkstra
  Constraint: 1 Second, 128MB
  Comment: x로 들어오는, x로부터 시작되는 케이스를 만들기 위해 메모리를 조금 더 쓰더라도 효율적인 풀이 작성. 만약 edge 하나로 할 경우 n + 1번 dijkstra를 진행해야 한다.
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, x, answer;
    static class Edge {
        int v, cost;

        Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static List<Edge>[] edgeB, edgeF;
    static int[] forward, backward;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        answer = 0;
        forward = new int[n + 1];
        backward = new int[n + 1];
        edgeB = new List[n + 1];
        edgeF = new List[n + 1];
        for (int i = 1; i <= n; ++i) {
            edgeB[i] = new ArrayList<>();
            edgeF[i] = new ArrayList<>();
            forward[i] = backward[i] = (int)1e9;
        }
        for (int i = 1; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeB[a].add(new Edge(b, c));
            edgeF[b].add(new Edge(a, c));
        }
    }

    static void dijkstra(int start, int[] path, List<Edge>[] info) {
        path[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            for (Edge nxt : info[cur.v]) {
                if(path[nxt.v] <= path[cur.v] + nxt.cost) continue;
                path[nxt.v] = path[cur.v] + nxt.cost;
                nxt.cost = path[nxt.v];
                pq.add(nxt);
            }
        }
    }

    static void solution() {
        dijkstra(x, backward, edgeB);
        dijkstra(x, forward, edgeF);
        for (int i = 1; i <= n; ++i) {
            answer = Math.max(answer, forward[i] + backward[i]);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
