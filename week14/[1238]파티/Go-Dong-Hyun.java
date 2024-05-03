package week14;

import java.util.*;
import java.io.*;

public class BOJ1238 {
    static class Edge implements Comparable<Edge> {
        int to;
        int weight;
        
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static ArrayList<ArrayList<Edge>> graph;
    static int N, M, X;

    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); 
        X = Integer.parseInt(st.nextToken()); 

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, t));
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            int[] lst1 = ans(i); 
            int[] lst2 = ans(X); 
            result = Math.max(result, lst1[X] + lst2[i]);
        }
        System.out.println(result);
    }

    static int[] ans(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        pq.offer(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int now = edge.to;
            int dist = edge.weight;

            if (distance[now] < dist) continue;

            for (Edge next : graph.get(now)) {
                int cost = dist + next.weight;
                
                if (cost < distance[next.to]) {
                    distance[next.to] = cost;
                    pq.offer(new Edge(next.to, cost));
                }
            }
        }
        return distance;
    }
}
