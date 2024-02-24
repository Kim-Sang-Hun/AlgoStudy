import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 전체 좌표의 조합으로 풀었다. dfs, bfs의 경우 한 방향으로 된 것만 되어 풀리지 않았다.
 * selected배열에 조합을 넣어주고, count가 7이 되면 연결된 것이 맞는지, 임도연파의 count가 4가 넘지 않는지 확인해준다.
 * 이후 연결된 것이 맞다면 result값을 1씩 늘려준다.
 */
public class JUN1941_소문난칠공주 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map = new char[5][5];
    static Point[] selected = new Point[7];
    static int[] dirY  = {0, 1, 0, -1};
    static int[] dirX = {1, 0, -1, 0};
    static int result;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }
        select(0, 0);
        System.out.println(result);
    }

    private static void select(int count, int start) {
        if (count == 7) {
            checkLinked();
            return;
        }
        for (int i = start; i < 25; i++) {
            selected[count] = new Point(i / 5, i % 5);
            select(count + 1, i + 1);
        }
    }

    private static void checkLinked() {
        boolean[] visited = new boolean[7];
        int cnt = 0;
        int countLim = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(selected[0]);
        visited[0] = true;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int y = cur.y;
            int x = cur.x;
            ++cnt;
            if (map[y][x] == 'Y') ++countLim;
            if (countLim >= 4) return;

            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dirY[i];
                int nX = cur.x + dirX[i];

                if (nY >= 5 || nY < 0 || nX >= 5 || nX < 0) continue;
                for (int j = 1; j < selected.length; j++) {
                    if (visited[j]) continue;
                    Point next = selected[j];
                    if (next.y == nY && next.x == nX) {
                        visited[j] = true;
                        q.add(next);
                    }
                }
            }
        }
        if (cnt == 7) ++result;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
