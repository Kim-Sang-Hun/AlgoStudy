import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, l, r;
    static int[][] a;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    //인구이동을 지속한다.
    //만약 isHappen을 통해 인구 변화가 감지되면 계속 진행할 수 있도록 true를 반환
    //그렇지 않다면 false를 반환하며 프로그램을 종료한다.
    static boolean movePopulation(){
        int[][] cmp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                cmp[i][j] = a[i][j];
            }
        }
        boolean[][] vis = new boolean[n][n];
        boolean isHappen = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if(vis[i][j]) continue;
                Deque<Point> q = new ArrayDeque<>();
                List<Point> allies = new ArrayList<>();
                int totalPopulation = 0;
                vis[i][j] = true;
                q.add(new Point(i, j));
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    allies.add(cur);
                    totalPopulation += a[cur.x][cur.y];
                    for (int dir = 0; dir < 4; ++dir) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];
                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                        if(vis[nx][ny]) continue;
                        int diff = Math.abs(a[cur.x][cur.y] - a[nx][ny]);
                        if(diff < l || diff > r) continue;
                        vis[nx][ny] = true;
                        q.add(new Point(nx, ny));
                    }
                }
                if(allies.isEmpty()) continue;
                totalPopulation /= allies.size();
                for (Point cur : allies) {
                    a[cur.x][cur.y] = totalPopulation;
                    if(a[cur.x][cur.y] != cmp[cur.x][cur.y]) isHappen = true;
                }
            }
        }
        return isHappen;
    }

    static void solution() throws IOException{
        int day = 0;
        while(movePopulation()){
            ++day;
        }
        System.out.println(day);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        a = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
