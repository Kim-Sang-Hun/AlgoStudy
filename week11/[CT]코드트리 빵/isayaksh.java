import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {0, -1, 1, 0};
    private static final int[] dy = {-1, 0, 0, 1};

    private static int N, M, answer = 0;

    private static int[][] board;           // 격자
    private static Point[] stores;          // 편의점

    private static Stack<Point> erase = new Stack<Point>();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int n = 0; n < N; n++) {
            board[n] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }

        stores = new Point[M+1];

        int x, y;
        for(int m = 1; m < M+1; m++) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;

            stores[m] = new Point(x, y, m, m); // (x, y, 편의점 번호, 경과 시간)
        }

        // ===================== end =====================

        Deque<Point> deque = new ArrayDeque<Point>();

        for(int t = 1; t < Integer.MAX_VALUE; t++) {

            int size = deque.size();

            if(t != 1 && size == 0) break;

            // 1. 격자에 있는 사람들 모두가 본인이 가고 싶은 편의점 방향을 향해서 1 칸 움직입니다.
            //    최단거리로 움직이며 최단 거리로 움직이는 방법이 여러가지라면 ↑, ←, →, ↓ 의 우선 순위로 움직이게 됩니다.
            //    여기서 최단거리라 함은 상하좌우 인접한 칸 중 이동가능한 칸으로만 이동하여 도달하기까지 거쳐야 하는 칸의 수가 최소가 되는 거리를 뜻합니다.

            while(0 < size--) {
                Point person = deque.poll();

                Way w = getWay(person);

                int nx = person.x + w.dx;
                int ny = person.y + w.dy;
                int no = person.no;

                // 2-1. 만약 편의점에 도착한다면 해당 편의점에서 멈추게 되고, 이때부터 다른 사람들은 해당 편의점이 있는 칸을 지나갈 수 없게 됩니다.
                if(stores[no].x == nx && stores[no].y == ny) {
                    erase.add(stores[no]);
                    answer = person.time;
                    continue;
                }

                deque.add(new Point(nx, ny, no, person.time+1));

            }

            
            // 2-2. 격자에 있는 사람들이 모두 이동한 뒤에 해당 칸을 지나갈 수 없어짐에 유의합니다.
            while (!erase.isEmpty()) {
                Point store = erase.pop();
                board[store.y][store.x] = -1;
            }

            // 3. 현재 시간이 t분이고 t ≤ m를 만족한다면, t번 사람은 자신이 가고 싶은 편의점과 가장 가까이 있는 베이스 캠프에 들어갑니다.
            //    여기서 가장 가까이에 있다는 뜻 역시 1에서와 같이 최단거리에 해당하는 곳을 의미합니다.
            //    가장 가까운 베이스캠프가 여러 가지인 경우에는 그 중 행이 작은 베이스캠프, 행이 같다면 열이 작은 베이스 캠프로 들어갑니다.
            //    t번 사람이 베이스 캠프로 이동하는 데에는 시간이 전혀 소요되지 않습니다.

            if(t <= M) {
                Point store = stores[t];
                Point basecamp = findBaseCamp(store);
                

                // 이때부터 다른 사람들은 해당 베이스 캠프가 있는 칸을 지나갈 수 없게 됩니다.
                // t번 사람이 편의점을 향해 움직이기 시작했더라도 해당 베이스 캠프는 앞으로 절대 지나갈 수 없음에 유의합니다.
                // 마찬가지로 격자에 있는 사람들이 모두 이동한 뒤에 해당 칸을 지나갈 수 없어짐에 유의합니다.
                board[basecamp.y][basecamp.x] = -1;
                basecamp.time = t;
                deque.add(basecamp);

            }

        }

        System.out.println(answer+1);

    }

    private static Way getWay(Point store) {

        Deque<Way> deque = new ArrayDeque<Way>();
        boolean[][] visited = new boolean[N][N];

        int nx, ny;
        Way w;

        for(int i = 0; i < 4; i++) {
            nx = store.x + dx[i];
            ny = store.y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] == -1 || visited[ny][nx]) continue;

            deque.add(new Way(nx, ny, dx[i], dy[i]));
            visited[ny][nx] = true;
        }

        while (!deque.isEmpty()) {
            
            w = deque.poll();

            if(w.x == stores[store.no].x && w.y == stores[store.no].y) return w;

            for(int i = 0; i < 4; i++) {
                nx = w.x + dx[i];
                ny = w.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] == -1) continue;

                visited[ny][nx] = true;
                deque.add(new Way(nx, ny, w.dx, w.dy));
            }


        }

        return null;

    }

    private static Point findBaseCamp(Point store) {

        Deque<Point> deque = new ArrayDeque<Point>();
        boolean[][] visited = new boolean[N][N];

        deque.add(new Point(store.x, store.y, store.no, 0));
        visited[store.y][store.x] = true;

        int nx, ny;
        Point p, basecamp;

        basecamp = new Point(N-1, N-1, store.no, Integer.MAX_VALUE);

        while (!deque.isEmpty()) {
            
            p = deque.poll();

            if(p.time > basecamp.time) continue;

            if(board[p.y][p.x] == 1) {
                if(p.time < basecamp.time) basecamp = new Point(p.x, p.y, store.no, p.time);

                if(p.time == basecamp.time) {
                    if(p.y < basecamp.y) basecamp = new Point(p.x, p.y, store.no, p.time);
                    if(p.y == basecamp.y && p.x < basecamp.x) basecamp = new Point(p.x, p.y, store.no, p.time);
                }
                
                continue;
            }

            for(int i = 0; i < 4; i++) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] == -1) continue;

                visited[ny][nx] = true;
                deque.add(new Point(nx, ny, store.no, p.time+1));
            }


        }

        return basecamp;
    }

    static class Way {
        int x, y, dx, dy;
        Way(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
    }


    static class Point {
        int x, y, no, time;
        Point(int x, int y, int no, int time) {
            this.x = x;
            this.y = y;
            this.no = no;
            this.time = time;
        }
    }
    
}
