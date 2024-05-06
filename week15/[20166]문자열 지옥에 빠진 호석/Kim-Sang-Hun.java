import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
먼저 입력받으면서 맵에 어떤 문자가 어떤 위치에 있는지 List<Point>로 기록한다.
이후 신이 좋아하는 문자열이 주어졌을 때 특정 문자가 존재하는지 확인하고
존재한다면 그 다음 문자와 연결되는지 확인한다.

연결되는지 확인하는 것은 8방향 전체를 탐색하지 않아도 가능하다.
환형으로 이어지므로 y좌표값과 x좌표값의 차이가 1 이하이거나 각각 n-1, m-1일 때 근처에 있는 것이다.
다만 차이가 둘 다 0이면 같은 지점이므로 패스한다.

문자열의 끝까지 통과한다면 cnt를 늘려준다.
메모리 : 19448kb, 시간 : 256ms
 */
public class JUN20166_문자열지옥에빠진호석 {

   static int n, m, k;
   static long cnt;
   static char[][] map;
   static String cur;
   static Map<Character, List<Point>> points;
   static Map<String, Long> counts;
   static class Point {
      int y, x;

      public Point(int y, int x) {
         this.y = y;
         this.x = x;
      }
   }
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      map = new char[n][m];
      points = new HashMap<>();
      counts = new HashMap<>();

      points = new HashMap<>();
      for (int i = 0; i < n; i++) {
         map[i] = br.readLine().toCharArray();
         for (int j = 0; j < m; j++) {
            char cur = map[i][j];
            List<Point> list = points.getOrDefault(cur, new ArrayList<>());
            list.add(new Point(i, j));
            points.put(cur, list);
         }
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < k; i++) {
         cur = br.readLine();
         sb.append(count()).append(System.lineSeparator());
      }
      System.out.print(sb);
   }

   private static long count() {
      cnt = 0;
      // 중복된 문자열일 경우 다시 계산하지 않고 Return
      if (counts.get(cur) != null) {
         return counts.get(cur);
      }
      List<Point> list = points.get(cur.charAt(0));
      if (list == null) {
         return 0;
      }
      for (Point p : list) {
         dfs(p, 1);
      }
      // 중복된 문자열 Return을 위해 저장
      counts.put(cur, cnt);
      return cnt;
   }

   private static void dfs(Point before, int idx) {
      if (idx >= cur.length()) {
         ++cnt;
         return;
      }
      List<Point> list = points.get(cur.charAt(idx));
      if (list == null) {
         return;
      }
      for (Point p : list) {
         if (isNear(before, p)) {
            dfs(p, idx + 1);
         }
      }
   }

   private static boolean isNear(Point p1, Point p2) {
      int y = Math.abs(p1.y - p2.y);
      int x = Math.abs(p1.x - p2.x);

      if (y == 0 && x == 0) return false;
      if ((y < 2 && x < 2) || (y == n-1 && x < 2) || (y < 2 && x == m-1) || (y == n-1 && x == m-1)) {
         return true;
      }
      return false;
   }
}
