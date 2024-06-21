import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
메모리 120000kb, 시간 668ms
bfs로 풀어주었다.
3차원 배열 visited를 통해 방문처리를 했다(0이면 벽을 부수지 않은 상태, 1이면 부순 상태)
class Point 내부에 brokenWall을 두어 벽을 부순 상태인지 아닌지 확인했다.
cnt변수로 얼마나 걸렸는지 세주었다.
*/
public class Main {
   static int n, m;
   static int[][] map;
   static boolean[][][] visited;
   static int[] start, end;
   static class Point {
      int x, y, cnt, brokenWall;

      public Point(int x, int y, int cnt, int brokenWall) {
         this.x = x;
         this.y = y;
         this.cnt = cnt;
         this.brokenWall = brokenWall;
      }
   }
   static int[] dx = {1, 0, -1, 0};
   static int[] dy = {0, -1, 0, 1};
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      map = new int[n + 1][m + 1];
      visited = new boolean[n + 1][m + 1][2];
      start = new int[2];
      end = new int[2];
      st = new StringTokenizer(br.readLine());
      start[0] = Integer.parseInt(st.nextToken());
      start[1] = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      end[0] = Integer.parseInt(st.nextToken());
      end[1] = Integer.parseInt(st.nextToken());
      for (int i = 1; i <= n; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 1; j <= m; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }

      int answer = -1;
      Queue<Point> qu = new LinkedList<>();
      qu.add(new Point(start[0], start[1], 0, map[start[0]][start[1]]));
      visited[start[0]][start[1]][map[start[0]][start[1]]] = true;

      while (!qu.isEmpty()) {
         Point cur = qu.poll();
         if (cur.x == end[0] && cur.y == end[1]) {
            answer = cur.cnt;
            break;
         }
         for (int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
            if (map[nx][ny] == 1) {
               if (cur.brokenWall == 1 || visited[nx][ny][1]) continue;
               visited[nx][ny][1] = true;
               qu.add(new Point(nx, ny, cur.cnt + 1, 1));
            } else {
               if (visited[nx][ny][cur.brokenWall]) continue;
               visited[nx][ny][cur.brokenWall] = true;
               qu.add(new Point(nx, ny, cur.cnt + 1, cur.brokenWall));
            }
         }
      }
      System.out.println(answer);
   }
}
