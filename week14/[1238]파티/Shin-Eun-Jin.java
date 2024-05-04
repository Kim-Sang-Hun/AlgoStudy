import java.io.*;
import java.util.*;

public class BOJ1238_파티 {
    static final int INF = 9900000;
    static int N, M, X;
    static ArrayList<ArrayList<Node>> list, reverseList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 도로의 수
        X = Integer.parseInt(st.nextToken()); // 파티할 집

        list = new ArrayList<>();
        reverseList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, weight));
            reverseList.get(end).add(new Node(start, weight));
        }

        int[] dist1 = dijkstra(list);
        int[] dist2 = dijkstra(reverseList);

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }

        System.out.println(ans);
    }

    public static int[] dijkstra(ArrayList<ArrayList<Node>> list) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));

        boolean[] isVisit = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (isVisit[cur.end]) {
                continue;
            }

            isVisit[cur.end] = true;
            for (Node next : list.get(cur.end)) {
                if (isVisit[next.end]) {
                    continue;
                }

                if (dist[next.end] > dist[cur.end] + next.weight) {
                    dist[next.end] = dist[cur.end] + next.weight;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node> {
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node arg) {
            return this.weight - arg.weight;
        }
    }
}
