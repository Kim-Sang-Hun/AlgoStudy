import java.io.*;
import java.util.*;

public class isayaksh {

    private static int N, A, B;
    private static int[] edge;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            edge = new int[N+1];
            visited = new boolean[N+1];

            for(int n = 0; n < N-1; n++) {
                st = new StringTokenizer(br.readLine());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                edge[B] = A;
            }

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            dfs(A);
            sb.append(dfs(B)).append("\n");

        }

        System.out.println(sb);

    }

    private static int dfs(int x) {
        if(visited[x] || edge[x] == 0) return x;
        visited[x] = true;
        return dfs(edge[x]);
    }

    
}
