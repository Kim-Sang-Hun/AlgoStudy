import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN1025_제곱수찾기 {

   static int N, M;
   static int max = -1;
   static int[][] map;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new int[N][M];

      for (int i = 0; i < N; i++) {
         String[] input = br.readLine().split("");
         for (int j = 0; j < M; j++) {
            map[i][j] = Integer.parseInt(input[j]);
         }
      }
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < M; j++) {
            findMax(i, j);
         }
      }
      System.out.print(max);
   }

   private static void findMax(int y, int x) {
      StringBuilder sb;
      int tmp, ny, nx;
      if (Math.sqrt(map[y][x]) == Math.floor(Math.sqrt(map[y][x]))) {
         max = Math.max(max, map[y][x]);
      }
      if (map[y][x] == 0) return;
      for (int i = -N; i < N; i++) {
         for (int j = -M; j < M; j++) {
            if (i == 0 && j == 0) continue;
            sb = new StringBuilder();
            sb.append(map[y][x]);
            ny = y;
            nx = x;
            while (true) {
               ny += i;
               nx += j;
               if (ny >= N || ny < 0 || nx >= M || nx < 0) break;
               sb.append(map[ny][nx]);
               tmp = Integer.parseInt(sb.toString());
               if (Math.sqrt(tmp) == Math.floor(Math.sqrt(tmp))) {
                  max = Math.max(max, tmp);
               }
            }
         }
      }
   }
}
