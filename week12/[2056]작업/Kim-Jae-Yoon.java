import java.util.*;
import java.io.*;
/*
  Title: 작업
  Tier: Gold 4
  Algorithm: Topological Sort
  Constraint: 2 Second, 256MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static char[][] area;
    static int[] indeg, time, answer;
    static List<Integer>[] edge;

    static void solution() {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            answer[i] = time[i];
            if(indeg[i] == 0) q.add(i);
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : edge[cur]) {
                if(--indeg[nxt] == 0){
                    q.add(nxt);
                }
                answer[nxt] = Math.max(answer[nxt], answer[cur] + time[nxt]);
            }
        }
        int ans = 0;
        for(int i = 1;i <= n; ++i){
            ans = Math.max(ans, answer[i]);
        }
        System.out.println(ans);
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        indeg = new int[n + 1];
        time = new int[n + 1];
        answer = new int[n + 1];
        edge = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) {
            edge[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; ++j) {
                int nxt = Integer.parseInt(st.nextToken());
                edge[nxt].add(i);
                ++indeg[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
