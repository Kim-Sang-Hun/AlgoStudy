import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, answer = 0;
    static int[] dx = {0,1,1};
    static int[] dy = {1,0,1};
    static int[][] a;

    //그냥 dfs
    static void dfs(int x, int y, int dir){
        if(x == n && y == n){
            ++answer;
            return;
        }
        for(int i = 0;i < 3; ++i){
            if((i == 0 && dir == 1) || (i == 1 && dir == 0)) continue;
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 1 || ny < 1 || nx > n || ny > n) continue;
            if(a[nx][ny] == 1) continue;
            if(i == 2 && (a[x][y + 1] == 1 || a[x + 1][y] == 1)) continue;
            dfs(nx,ny, i);
        }
    }

    static void solution(){
        dfs(1, 2, 0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = new int[n + 1][n + 1];
        for(int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1;j <= n; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
    }

}
