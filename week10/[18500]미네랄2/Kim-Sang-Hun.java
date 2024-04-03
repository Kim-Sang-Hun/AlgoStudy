import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 지옥의 구현문제. 3시간 박았다
* 왼쪽 오른쪽 번갈아가며 던지다가 미네랄에 닿으면 그 미네랄 .으로 바꿔주고 위, 아래, 반대방향 체크해서 땅에 닿지 않는 미네랄이 있는지 확인한다
* 만약 땅에 닿지 않는다면 그 미네랄이 땅에 닿거나 다른 미네랄에 닿기 전까지 낮춰준다
* 땅에 닿거나 다른 미네랄에 닿는 조건을 확인하는게 쉽지 않았다.
* 그래서 List에 모든 미네랄모임을 넣어주고 그 미네랄위치를 모두 .으로 바꿔준 다음 미네랄 위치를 한칸 아래로 옮겨주고 그 위치 아래가 x이거나 땅에 닿는지 확인해줬다.
*/

public class Main {

   static int r, c;
   static char[][] map;
   static boolean[][] visited;
   static boolean modified;
   static int[] dy = {0, 1, 0, -1};
   static int[] dx = {1, 0, -1, 0};

   static class Mineral {

      int y, x;

      public Mineral(int y, int x) {
         this.y = y;
         this.x = x;
      }
   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      r = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());
      map = new char[r][c];
      for (int i = 0; i < r; i++) {
         String str = br.readLine();
         for (int j = 0; j < c; j++) {
            map[i][j] = str.charAt(j);
         }
      }
      int n = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
         int height = r - Integer.parseInt(st.nextToken());
         if (i % 2 == 0) {
            for (int j = 0; j < c; j++) {
               if (map[height][j] == 'x') {
                  map[height][j] = '.';
                  modified = false;
                  if (j + 1 < c && map[height][j + 1] == 'x') {
                     checkAndFall(height, j + 1);
                  }
                  if (modified) break;
                  if (height - 1 >= 0 && map[height - 1][j] == 'x') {
                     checkAndFall(height - 1, j);
                  }
                  if (modified) break;
                  if (height + 1 < r && map[height + 1][j] == 'x') {
                     checkAndFall(height + 1, j);
                  }
                  break;
               }
            }
         } else {
            for (int j = c - 1; j >= 0; j--) {
               if (map[height][j] == 'x') {
                  map[height][j] = '.';
                  modified = false;
                  if (j - 1 >= 0 && map[height][j - 1] == 'x') {
                     checkAndFall(height, j - 1);
                  }
                  if (modified) break;
                  if (height - 1 >= 0 && map[height - 1][j] == 'x') {
                     checkAndFall(height - 1, j);
                  }
                  if (modified) break;
                  if (height + 1 < r && map[height + 1][j] == 'x') {
                     checkAndFall(height + 1, j);
                  }
                  break;
               }
            }
         }
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < r; i++) {
         for (int j = 0; j < c; j++) {
            sb.append(map[i][j]);
         }
         if (i != r-1) sb.append(System.lineSeparator());
      }
      System.out.print(sb);
   }

   public static void checkAndFall(int y, int x) {
      visited = new boolean[r][c];
      visited[y][x] = true;
      Queue<Mineral> qu = new ArrayDeque<>();
      List<Mineral> list = new ArrayList<>();
      Mineral m = new Mineral(y, x);
      list.add(m);
      qu.add(m);

      while (!qu.isEmpty()) {
         Mineral cur = qu.poll();

         for (int i = 0; i < 4; i++) {
            int ny = cur.y + dy[i];
            int nx = cur.x + dx[i];

            if (ny >= r) {
               return;
            }
            if (ny < 0 || nx >= c || nx < 0 || map[ny][nx] == '.' || visited[ny][nx]) {
               continue;
            }
            Mineral next = new Mineral(ny, nx);
            visited[ny][nx] = true;
            list.add(next);
            qu.add(next);
         }
      }
      modified = true;
      boolean touchBottom = false;
      while (!touchBottom) {
         for (Mineral mi : list) {
            map[mi.y++][mi.x] = '.';
         }
         for (Mineral mi : list) {
            if (mi.y + 1 >= r || map[mi.y + 1][mi.x] == 'x') {
               touchBottom = true;
               break;
            }
         }
         for (Mineral mi : list) {
            map[mi.y][mi.x] = 'x';
         }
      }
   }
}
