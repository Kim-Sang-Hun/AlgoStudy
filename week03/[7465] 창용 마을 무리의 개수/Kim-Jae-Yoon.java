import java.io.*;
import java.util.*;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, m, answer;

  //dijkstra
    static void solution(Info[] link) {
        answer = 0;
        boolean[] vis = new boolean[n + 1];
        for(int i = 1;i <= n; ++i){
            if(vis[i]) continue;
            vis[i] = true;
            ++answer;
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            while (!q.isEmpty()) {
                int cur = q.poll();
                for(int nxt : link[cur].adj){
                    if(vis[nxt]) continue;
                    vis[nxt] = true;
                    q.add(nxt);
                }
            }
        }
    }

  //dijkstra 사용을 위한 정점에서의 연결 정점 모음
    static class Info{
        List<Integer> adj;
        Info() {
            adj = new ArrayList<>();
        }
    }
 
    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int a, b;
            Info []link = new Info[n + 1];
            for(int j = 1;j <= n; ++j){
                link[j] = new Info();
            }
            for(int j = 0;j < m; ++j){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                link[a].adj.add(b);
                link[b].adj.add(a);
            }
            solution(link);
            sb.append('#').append(i).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
