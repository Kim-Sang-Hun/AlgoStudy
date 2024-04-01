import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[][] a;
    static int [][][]dp;
    static final int MAX = (int)1e9;
    static class Info{
        int x, y, cnt;
        Info(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static void Solution(){
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(1, 1, 1));
        dp[1][1][1] = 1;
        while(!q.isEmpty()) {
            Info cur = q.poll();
            for(int i = 0;i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 1 || ny < 1 || nx > n || ny > m) continue;
                if(a[nx][ny] == 1 && cur.cnt == 1){
                    dp[nx][ny][0] = dp[cur.x][cur.y][1] + 1;
                    q.add(new Info(nx, ny, 0));
                }
                if(a[nx][ny] == 0 && dp[nx][ny][cur.cnt] == MAX){
                    dp[nx][ny][cur.cnt] = dp[cur.x][cur.y][cur.cnt] + 1;
                    q.add(new Info(nx, ny, cur.cnt));
                }
            }
        }
        if(dp[n][m][0] == MAX && dp[n][m][1] == MAX){
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(dp[n][m][0], dp[n][m][1]));
    }

  //3차원 dp로 벽을 뚫었을 경우, 안 뚫었을 경우에 대해 고려함
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1][2];
        for(int i = 1; i <= n; ++i) {
            String line = br.readLine();
            for(int j = 1;j <= m; ++j) {
                a[i][j] = line.charAt(j - 1) - '0';
                dp[i][j][0] = dp[i][j][1] = MAX;
            }
        }
        if(n == 1 && m == 1) {
            System.out.println(1);
            return;
        }
        Solution();
    }
}
