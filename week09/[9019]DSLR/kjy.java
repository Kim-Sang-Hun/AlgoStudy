import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int a, b;
    static Queue<Integer> q;

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
    }

    static void solution() {
        boolean[] vis = new boolean[10000];
        String[] cmd = new String[10000];
        Arrays.fill(cmd, "");
        q = new ArrayDeque<>();
        q.add(a);
        vis[a] = true;
        int d, s, l, r;
        while (!q.isEmpty()) {
            int cur = q.poll();
            if(cur == b) break;
            //d
            d = (cur << 1) % 10000;
            //s
            s = cur - 1;
            if(s == -1) s = 9999;
            //l -> d2, d3, d4, d1
            l = (cur % 1000) * 10 + cur / 1000;
            //r -> d4, d1, d2, d3
            r = (cur % 10) * 1000 + cur / 10;

            //후처리
            if(!vis[d]){
                vis[d] = true;
                q.add(d);
                cmd[d] = cmd[cur] + "D";
            }
            if(!vis[s]){
                vis[s] = true;
                q.add(s);
                cmd[s] = cmd[cur] + "S";
            }
            if(!vis[l]){
                vis[l] = true;
                q.add(l);
                cmd[l] = cmd[cur] + "L";
            }
            if(!vis[r]){
                vis[r] = true;
                q.add(r);
                cmd[r] = cmd[cur] + "R";
            }
        }
        sb.append(cmd[b]).append('\n');
    }

    public static void main(String[] args) throws IOException{
        int t = Integer.parseInt(br.readLine());
        for(int i = 1;i <= t; ++i) {
            input();
            solution();
        }
        System.out.println(sb);
    }
}
