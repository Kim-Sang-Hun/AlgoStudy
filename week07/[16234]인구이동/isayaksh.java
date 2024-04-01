import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N, L, R;
    private static int[][] graph;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for(int n = 0; n < N; n++) {
            graph[n] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }

        boolean flag;
        int time = 0;

        while(true) {

            visited = new boolean[N][N];
            flag = false;

            for(int y = 0; y < N; y++) {
                for(int x = 0; x < N; x++) {

                    if(visited[y][x]) continue;

                    List<Point> union = BFS(x, y);

                    if(union.size() == 1) continue;

                    flag = true;

                    int peopleAverage = getPeopleAverage(union);
                    for(Point p : union) {
                        graph[p.y][p.x] = peopleAverage;
                    }

                }
            }

            if(!flag) break;

            time++;

        }

        System.out.println(time);

    }

    private static List<Point> BFS(int x, int y) {

        int nx, ny;

        visited[y][x] = true;

        List<Point> union = new ArrayList<Point>();
        union.add(new Point(x, y));

        Deque<Point> deque = new ArrayDeque<Point>();
        deque.add(new Point(x, y));

        while (!deque.isEmpty()) {
            Point p = deque.poll();

            for(int dir = 0; dir < 4; dir++) {
                nx = p.x + dx[dir];
                ny = p.y + dy[dir];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) continue;

                int gap = Math.abs(graph[p.y][p.x] - graph[ny][nx]);
                if(gap < L || R < gap) continue;

                visited[ny][nx] = true;
                deque.add(new Point(nx, ny));
                union.add(new Point(nx, ny));
            }

        }

        return union;

    }

    private static int getPeopleAverage(List<Point> union) {
        int peopleAverage = 0;

        for(Point p : union) {
            peopleAverage += graph[p.y][p.x];
        }

        return peopleAverage / union.size();
    }

    
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
