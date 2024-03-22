import java.io.*;
import java.util.*;

/*
  물론 제 풀이 자체가 좀 이상했을 수도 있지만, 사실 좀 이상한? 혹은 너무 각박한 문제같다.
  int 처리과정이 bool 처리과정보다 조금 더 빠른 건 알고 있었지만,
  진짜 그거때메 시간초과가 날 거라고는 생각도 안 하다가, 설마하면서 바꿔봤더니 정답이 되었습니다.

  O(N * (N + M))인데, SCC 알고리즘 풀이법 보면 진짜 당황스러우니 참고해보세요.
*/

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static List<Integer>[] edge;
    static int[] vis;
    static int[] answer;

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edge = new ArrayList[n + 1];
        answer = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            edge[i] = new ArrayList<>();
        }
        int a, b;
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
        }
    }

  /*
    풀이에서 신뢰관계로만 방향을 진행해야 한다는 고정 관념에서 잠깐 벗어나기 위해
    edge[b].add(a)가 아니라 edge[a].add(b)로 받았다.
  */

    //사실 ++answer[nxt]를 dp로 진행해보려 했으나 의문의 시간 초과로 그려려니 하고 넘김
    //dp 사용 당시 dp + dfs를 사용했는데, 최악의 경우 시간 초과 발생 가능성을 고려하면 dp + bfs까지가 한계일 듯 합니다.
    static void bfs(int start) {
        vis = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        vis[start] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : edge[cur]) {
                if(vis[nxt] == 1) continue;
                vis[nxt] = 1;
                ++answer[nxt]; 
                q.add(nxt);
            }
        }
    }

    static void solution() {
        int maxCnt = 0;
        for(int i = 1;i <= n; ++i){
            bfs(i);
        }
        for (int i = 1; i <= n; ++i) {
            if(maxCnt < answer[i]) maxCnt = answer[i];
        }
        for (int i = 1; i <= n; ++i) {
            if(maxCnt == answer[i]) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException{
        input();
        solution();
    }
}
