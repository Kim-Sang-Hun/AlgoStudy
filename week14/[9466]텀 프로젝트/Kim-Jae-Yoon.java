import java.util.*;
import java.io.*;
/*
  Title: 텀 프로젝트
  Tier: Gold 3
  Algorithm: DFS
  Constraint: 3 Second, 256MB
  Comment: 단순 방문한 케이스 외에도, 사이클이 생기는 케이스에 대해서 조금 고민이 필요한 문제입니다.
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, answer;
    static int[] team;
    static boolean[] vis, finish;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        answer = 0;
        team = new int[n + 1];
        vis = new boolean[n + 1];
        finish = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            team[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void search(int cur) {
        if(finish[cur]) return;
        if(vis[cur]) {
            finish[cur] = true;
            ++answer;
        }
        vis[cur] = true;
        search(team[cur]);
        finish[cur] = true;
        vis[cur] = false;
    }

    static void solution() {
        for (int i = 1; i <= n; ++i) {
            if(finish[i]) continue;
            search(i);
        }
        sb.append(n - answer).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 1;i <= t; ++i) {
            input();
            solution();
        }
        System.out.print(sb);
    }
}
