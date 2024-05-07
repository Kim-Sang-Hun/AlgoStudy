import java.util.*;
import java.io.*;

/*
  Title: 영우는 사기꾼?
  Tier: Gold 3
  Algorithm: Topological Sort
  Constraint: 1 Second, 512MB
*/

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k;
    static int[] indeg, cnt;
    static List<Integer>[] edge, info;
    static Map<Integer, Integer> map = new HashMap<>();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        indeg = new int[n + 1];
        cnt = new int[n + 1];
        edge = new ArrayList[n + 1];
        info = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) {
            edge[i] = new ArrayList<>();
            info[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
            ++indeg[b];
        }
    }

    static void solution() throws IOException{
        boolean possible = true;
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(!possible) continue;
            if (x == 1) {
                if(indeg[y] > 0){
                    possible = false;
                    continue;
                }
                if(++cnt[y] > 1) continue;
                for (int nxt : edge[y]) {
                    --indeg[nxt];
                }
            } else {
                if(cnt[y] == 0){
                    possible = false;
                    continue;
                }
                if(--cnt[y] > 0) continue;
                for (int nxt : edge[y]) {
                    ++indeg[nxt];
                }
            }
        }
        if(possible) System.out.println("King-God-Emperor");
        else System.out.println("Lier!");
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
