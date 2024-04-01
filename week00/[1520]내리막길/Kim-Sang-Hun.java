import java.util.*;

public class Main {

  static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  static int map[][], dp[][], M, N;

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    M = s.nextInt();
    N = s.nextInt();
    map = new int[M][N];
    dp = new int[M][N];

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j] = s.nextInt();
        dp[i][j] = -1;
      }
    }
    System.out.println(DFS(0, 0));
  }

  public static int DFS(int y, int x) {
    if (y == M - 1 && x == N - 1) {
      return 1;
    }
    if (dp[y][x] != -1) {
      return dp[y][x];
    }

    dp[y][x] = 0;
    for (int i = 0; i < 4; i++) {
      int nY = y + dirs[i][0];
      int nX = x + dirs[i][1];

      if (nY >= 0 && nY < M && nX >= 0 && nX < N) {
        if (map[nY][nX] < map[y][x]) {
          dp[y][x] += DFS(nY, nX);
        }
      }
    }
    return dp[y][x];
  }
}
