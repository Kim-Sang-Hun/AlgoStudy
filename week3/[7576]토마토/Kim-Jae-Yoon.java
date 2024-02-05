import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, m, answer;
    static int[] dx = new int[]{0,1,0,-1};
    static int[] dy = new int[]{1,0,-1,0};
    static int[][] board, dist;
    static Queue<Point> q = new ArrayDeque<>();

    static void solution() {
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir = 0;dir < 4; ++dir){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(dist[nx][ny] >= 0) continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                q.add(new Point(nx, ny));
            }
        }
        for(int i = 0;i < n; ++i){
            for(int j = 0;j < m; ++j){
                if(dist[i][j] == -1){
                    System.out.println(-1);
                    return;
                }
                answer = Math.max(answer, dist[i][j]);
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dist = new int[n][m];
        for(int i = 0;i < n; ++i){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < m; ++j){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1){
                    q.add(new Point(i, j));
                }
                else if(board[i][j] == 0){
                    dist[i][j] = -1;
                }
            }
        }
        solution();
        System.out.println(sb);
    }
}
