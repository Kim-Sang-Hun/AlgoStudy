import java.io.*;
import java.util.*;

public class Kim-Sang-Hyun {

    public static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M, x, y;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parent = new int[N+1];
            for(int i = 0; i < N + 1; i++) {
                parent[i] = i;
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                union(x, y);
            }

            Set<Integer> set = new HashSet<Integer>();
            for(int i = 1; i < N+1; i++) {
                set.add(find(i));
            }
            System.out.println("#" + t + " " + set.size());
        }

    }

    public static int find(int x) {
        if(x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parent[Math.max(x, y)] = Math.min(x, y);
    }
}
