import java.awt.Point;
import java.util.*;
import java.io.*;
/*
  Title: 벽 부수고 이동하기 4
  Tier: Gold 2
  Algorithm: BFS
  Constraint: 2 Second, 512MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, m, idx;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] area, answer;
    static HashMap<Integer, Integer> map;
    static boolean[][] vis;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        area = new int[n][m];
        answer = new int[n][m];
        vis  = new boolean[n][m];
        map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            String tmp = br.readLine();
            for (int j = 0; j < m; ++j) {
                area[i][j] = tmp.charAt(j) - '0';
            }
        }
        idx = 2;
    }

    static void bfs(int x, int y) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        vis[x][y] = true;
        area[x][y] = idx;
        int cnt = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            ++cnt;
            for (int dir = 0; dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(vis[nx][ny] || area[nx][ny] > 0) continue;
                vis[nx][ny] = true;
                area[nx][ny] = idx;
                q.add(new Point(nx, ny));
            }
        }
        map.put(idx++, cnt);
    }

    static void solution() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (area[i][j] >= 1) continue;
                bfs(i, j);
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if(area[i][j] != 1) {
                    sb.append(0);
                    continue;
                }
                Set<Integer> set = new HashSet<>();
                for (int dir = 0; dir < 4; ++dir) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if(area[nx][ny] == 1) continue;
                    set.add(area[nx][ny]);
                }
                for (int nxt : set) {
                    answer[i][j] += map.get(nxt);
                }
                answer[i][j] = (++answer[i][j]) % 10;
                sb.append(answer[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
