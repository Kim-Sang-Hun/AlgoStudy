import java.util.Arrays;
import java.util.Scanner;
/*
메모리 148000, 시간 552
재귀호출해서 가장 작은 단위를 출력한다.
*/
public class Main {
   static char[][] map;

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int N = sc.nextInt();

      map = new char[N][N * 2 - 1];
      for (int i = 0; i < N; i++) {
         Arrays.fill(map[i], ' ');
      }

      dfs(0, N-1, N);

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < 2 * N - 1; j++) {
            sb.append(map[i][j]);
         }
         sb.append('\n');
      }
      System.out.println(sb);
   }

   public static void dfs(int y, int x, int len) {
      if (len == 3) {
         for (int i = 0; i < 3; i++) {
            for (int j = -i; j <= i; j++) {
               map[y + i][x + j] = '*';
            }
         }
         map[y + 1][x] = ' ';
      } else {
         int half = len / 2;
         dfs(y, x, half);
         dfs(y + half, x - half, half);
         dfs(y + half, x + half, half);
      }
   }

}
