import java.io.*;
import java.util.*;

public class isayaksh {

    private static final int[] dx = {0, -1, 1, 0};
    private static final int[] dy = {-1, 0, 0, 1};

    private static int R, C, N;
    private static int[] order;
    private static char[][] board;
    private static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for(int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());
        order = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
        
        for(int turn = 1; turn < N + 1; turn++) {

            int x, y;

            // 1. 막대 던지기
            y = R - order[turn-1]; 
            x = (turn%2==1) ? throwStickFromLeft(y) : throwStickFromRight(y);
            if(x == -1) continue;

            // 2. 미네랄 파괴
            board[y][x] = '.';

            // 3. 새롭게 생성된 클러스터 찾기
            int nx, ny, height;
            checked = new boolean[R][C];
            for(int dir = 0; dir < 4; dir++) {
                nx = x + dx[dir];
                ny = y + dy[dir];

                // 3-1. 범위를 벗어나거나 or 이미 클러스터로 사용된 경우 제외
                if(nx < 0 || nx >= C || ny < 0 || ny >= R || board[ny][nx] == '.' || checked[ny][nx]) continue;

                // 3-2. 클러스터 확인
                List<Point> cluster = findCluster(nx, ny);

                for(Point c : cluster) {
                    board[c.y][c.x] = '.';
                }

                // 3-4. 클러스터가 떨어지는 높이
                height = 1;
                while(true) {
                    if(fallDownPossible(cluster, height)) {
                        height++;
                    } else {
                        height--;
                        break;
                    }
                }

                // 3-5. 클러스터 떨어짐
                for(Point c : cluster) {
                    board[c.y+height][c.x] = 'x';
                }

            }

        }
        
        print();

    }

    private static int throwStickFromLeft(int y) {

        for(int x = 0; x < C; x++) {
            if(board[y][x] == 'x') return x;
        }

        return -1;
    }

    private static int throwStickFromRight(int y) {

        for(int x = C-1; x >= 0; x--) {
            if(board[y][x] == 'x') return x;
        }

        return -1;
    }

    private static List<Point> findCluster(int x, int y) {

        List<Point> cluster = new ArrayList<Point>();
        Deque<Point> deque = new ArrayDeque<Point>();
        
        cluster.add(new Point(x, y));
        deque.add(new Point(x, y));
        checked[y][x] = true;


        int nx, ny;
        while(!deque.isEmpty()) {
            Point p = deque.poll();

            for(int dir = 0; dir < 4; dir++) {
                nx = p.x + dx[dir];
                ny = p.y + dy[dir];

                if(nx < 0 || nx >= C || ny < 0 || ny >= R || board[ny][nx] == '.' || checked[ny][nx]) continue;

                checked[ny][nx] = true;
                cluster.add(new Point(nx, ny));
                deque.add(new Point(nx, ny));

            }

        }


        return cluster;
    }

    private static boolean fallDownPossible(List<Point> cluster, int height) {
        for(Point c : cluster) {
            if(c.y + height >= R || board[c.y + height][c.x] == 'x') return false;
        }
        return true;
    }

    private static void print() {
        for(int y = 0; y < R; y++) {
            for(int x = 0; x < C; x++) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }


    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
