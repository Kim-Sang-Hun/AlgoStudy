import java.util.*;
import java.io.*;

public class isayaksh {

    private static int N, M, X, A, B;
    private static List<Integer>[] up, down;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        up = new List[N+1];
        down = new List[N+1];

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if(up[B] == null) up[B] = new ArrayList<Integer>();
            up[B].add(A);

            if(down[A] == null) down[A] = new ArrayList<Integer>();
            down[A].add(B);

        }

        visited = new boolean[N+1];
        visited[X] = true;
        int u = upDFS(X);

        visited = new boolean[N+1];
        visited[X] = true;
        int d = downDFS(X);

        System.out.println((1 + u) + " " + (N - d));

    }

    private static int upDFS(int x) {

        int count = 0;

        if(up[x] == null) return count;

        for(Integer next : up[x]) {
            if(visited[next]) continue;
            visited[next] = true;
            count += 1 + upDFS(next);
        }

        return count;

    }

    private static int downDFS(int x) {
        int count = 0;

        if(down[x] == null) return count;

        for(Integer next : down[x]) {
            if(visited[next]) continue;
            visited[next] = true;
            count += 1 + downDFS(next);
        }

        return count;
    }

}
