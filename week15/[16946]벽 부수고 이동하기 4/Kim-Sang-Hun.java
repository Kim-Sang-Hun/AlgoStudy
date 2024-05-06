import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
1000*1000이라서 1이 나올 때마다 bfs를 돌리면 시초가 날 것 같았다.
그래서 먼저 이어진 0들을 특정 번호로 매핑해두고 map에 이어진 0들의 개수를 저장해두었다.
이후 맵을 돌면서 1이 나오면 사방탐색해서 매핑된 값들을 더해주었다.
Set을 활용해 중복된 번호를 더하는 것을 막았다.
메모리 240000kb, 시간 1580ms
 */
public class JUN16946_벽부수고이동하기4 {

   static int n, m, union;
   static int[][] map;
   static int[][] uNumber;
   static Map<Integer, Integer> cnt;
   static int[] dy = {-1, 0, 1, 0};
   static int[] dx = {0, 1, 0, -1};
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      map = new int[n][m];
      uNumber = new int[n][m];
      cnt = new HashMap<>();
      for (int i = 0; i < n; i++) {
         map[i] = Arrays.stream(br.readLine().split(""))
             .mapToInt(Integer::parseInt).toArray();
      }
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            if (map[i][j] == 0 && uNumber[i][j] == 0) {
               mapZeros(i, j);
            }
         }
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            if (map[i][j] == 1) {
               map[i][j] = countZeros(i, j);
            }
            sb.append(map[i][j] % 10);
         }
         sb.append(System.lineSeparator());
      }
      System.out.print(sb);
   }

   private static void mapZeros(int y, int x) {
      ++union;
      int c = 0;
      Queue<int[]> qu = new ArrayDeque<>();
      qu.add(new int[]{y, x});
      uNumber[y][x] = union;
      while (!qu.isEmpty()) {
         int[] cur = qu.poll();
         ++c;

         for (int i = 0; i < 4; i++) {
            int ny = cur[0] + dy[i];
            int nx = cur[1] + dx[i];

            if (ny >= n || ny < 0 || nx >= m || nx < 0 ||
                uNumber[ny][nx] != 0 || map[ny][nx] == 1) continue;
            qu.add(new int[]{ny, nx});
            uNumber[ny][nx] = union;
         }
      }
      cnt.put(union, c);
   }

   private static int countZeros(int y, int x) {
      int c = 1;
      Set<Integer> visited = new HashSet<>();

      for (int i = 0; i < 4; i++) {
         int ny = y + dy[i];
         int nx = x + dx[i];

         if (ny >= n || ny < 0 || nx >= m || nx < 0 || uNumber[ny][nx] == 0) continue;
         int union = uNumber[ny][nx];
         if (!visited.contains(union)) {
            visited.add(union);
            c += cnt.get(union);
         }
      }
      return c;
   }

}
