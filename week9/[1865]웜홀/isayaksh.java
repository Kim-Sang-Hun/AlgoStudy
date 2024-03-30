import java.util.*;
import java.io.*;
 
public class isayaksh {
    static int N, M, W;
    static int[] distance;
    static ArrayList<Node>[] edges;
    static final int INF = 987654321;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
 
            distance = new int[N + 1];
            edges = new ArrayList[N+1];
            for(int n = 1; n < N+1; n++) {
                edges[n] = new ArrayList<Node>();
            }
 
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges[S].add(new Node(E, T));
                edges[E].add(new Node(S, T));

            }

            for(int w = 0; w < W; w++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges[S].add(new Node(E, -T));
            }
 
            sb.append(bellmanFord() ?"YES\n" : "NO\n");

        }
 
        System.out.println(sb);

    }
    
    public static boolean bellmanFord() {

        int[] distance = new int[N+1];
        boolean isUpdate = false;

        for(int n  = 1; n < N; n++) {
            isUpdate = false;
            for(int S = 1; S < N+1; S++) {
                for(Node node : edges[S]) {
                    if(distance[node.E] > distance[S] + node.T) {
                        isUpdate = true;
                        distance[node.E] = distance[S] + node.T;
                    }
                }
            }
            if(!isUpdate) break;
        }
        if (isUpdate) {
            for (int S = 1; S < N+1; S++) {
                for (Node node : edges[S]) {
                    if (distance[node.E] > distance[S] + node.T) {
                        return true;
                    }
                }
            }
        }
 
        return false;
    }

    static class Node {
        int E, T;

        public Node(int E, int T) {
            this.E = E;
            this.T = T;
        }

    }
 
}
