import java.util.*;
import java.io.*;
/*
  Title: 가장 가까운 공통 조상
  Tier: Gold 4
  Algorithm: LCS
  Constraint: 1 Second, 128MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, src, dst;
    static int[] parent;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            parent[i] = i;
        }
        for (int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            parent[c] = p;
        }
        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dst = Integer.parseInt(st.nextToken());
    }

    static void solution() {
        while (true) {
            if(src == parent[src]) {
                parent[src] = dst;
                break;
            }
            int temp = src;
            src = parent[src];
            if (src == dst) {
                sb.append(dst).append('\n');
                return;
            }
            parent[temp] = dst;
        }
        int path = dst;
        while (true) {
            if (dst == parent[path]) {
                sb.append(path).append('\n');
                return;
            }
            path = parent[path];
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 1;i <= t; ++i) {
            input();
            solution();
        }
        System.out.println(sb);
    }
}
