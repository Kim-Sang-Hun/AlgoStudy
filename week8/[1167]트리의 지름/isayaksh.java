import java.util.*;
import java.io.*;

public class isayaksh {

    private static int N;
    private static List<Node>[] edge;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        edge = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) {
            edge[i] = new ArrayList<Node>();
        }

        for(int i = 1; i < N+1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

            for(int j = 1; j < input.length-2; j+=2) {
                edge[input[0]].add(new Node(input[j], input[j+1]));
            }
        }

        Node node = bfs(1);

        node = bfs(node.V);

        System.out.println(node.W);

    }

    public static Node bfs(int start) {

        int[] weight = new int[N+1];
        Arrays.fill(weight, -1);
        weight[start] = 0;

        Deque<Integer> deque = new ArrayDeque<Integer>();
        deque.add(start);

        int V, W;

        while(!deque.isEmpty()) {
            int node = deque.poll();

            for(Node nextNode : edge[node]) {
                V = nextNode.V;
                W = nextNode.W;

                if(weight[V] != -1) continue;

                weight[V] = weight[node] + W;
                deque.add(V);

            }
        }

        Node answer = new Node(0, 0);

        for(int node = 1; node < N+1; node++) {
            if(answer.W < weight[node]) {
                answer.V = node;
                answer.W = weight[node];
            }
        }

        return answer;

    }

    static class Node {
        int V, W;

        public Node(int V, int W) {
            this.V = V;
            this.W = W;
        }

    }

}
