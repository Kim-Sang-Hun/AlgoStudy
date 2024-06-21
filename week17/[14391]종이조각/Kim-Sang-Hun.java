import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
메모리 30만 kb, 시간 1200ms가 걸린 역대급 문제.
그냥 모든 경우의 수를 구해주었다...
*/
public class Main {
   static int n, m, max;
   static String[][] map;
   static boolean[][] visited;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      map = new String[n][m];
      visited = new boolean[n][m];

      for (int i = 0; i < n; i++) {
         map[i] = br.readLine().split("");
      }
      dfs(0);
      System.out.println(max);
   }

   private static void dfs(int sum) {
      boolean allVisited = true;
      checkVisit:
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            if (!visited[i][j]) {
               allVisited = false;
               break checkVisit;
            }
         }
      }
      if (allVisited) {
         max = Math.max(max, sum);
         return;
      }
      StringBuilder sb;
      // 시작점 찾기
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            if (visited[i][j]) continue;
            sb = new StringBuilder();
            // 시작한 적 없는 가장 왼쪽 위 점, 아래쪽과 오른쪽 다 가보기
            int stopPoint = Integer.MAX_VALUE;
            for (int k = 0; k < n - i; k++) {
               if (visited[i + k][j]) {
                  stopPoint = k;
                  break;
               }
               sb.append(map[i + k][j]);
               visited[i + k][j] = true;
               dfs(sum + Integer.parseInt(sb.toString()));
            }
            for (int k = 0; k < n - i; k++) {
               if (k == stopPoint) break;
               visited[i + k][j] = false;
            }
            sb = new StringBuilder();
            stopPoint = Integer.MAX_VALUE;
            for (int k = 0; k < m - j; k++) {
               if (visited[i][j+k]) {
                  stopPoint = k;
                  break;
               }
               sb.append(map[i][j + k]);
               visited[i][j + k] = true;
               dfs(sum + Integer.parseInt(sb.toString()));
            }
            for (int k = 0; k < m - j; k++) {
               if (k == stopPoint) break;
               visited[i][j + k] = false;
            }
            // 시작한 적 없는 가장 왼쪽 위 점을 했으니, 이후 for문은 의미가 없으므로 종료
            return;
         }
      }
   }
}
