import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N, K, L;
    private static int[][] graph;

    private static Deque<Order> orders = new ArrayDeque<Order>();
    private static Deque<Point> snake = new ArrayDeque<Point>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];

        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[r][c] = 9;
        }

        L = Integer.parseInt(br.readLine());

        for(int l = 0; l < L; l++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            orders.add(new Order(x, c));

        }

        int nx, ny;
        int time = 0;
        int direction = 0;

        snake.add(new Point(1, 1));
        graph[1][1] = 1;
        
        while(true) {

            // 0. 방향 전환
            if(!orders.isEmpty() && orders.peekFirst().time == time) {
                Order order = orders.pollFirst();

                if(order.turnabout.equals("L")) {
                    direction = (direction + 3) % 4;
                }

                if(order.turnabout.equals("D")) {
                    direction = (direction + 1) % 4;
                }
                
            }

            time++;

            // 1. 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
            Point head = snake.peekFirst();
            nx = head.x + dx[direction];
            ny = head.y + dy[direction];
            snake.addFirst(new Point(nx, ny));

            // 2. 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
            if(nx < 1 || nx > N || ny < 1 || ny > N || graph[ny][nx] == 1) break;

            // 3. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
            // 4. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
            if(graph[ny][nx] != 9) {
                Point tail = snake.pollLast();
                graph[tail.y][tail.x] = 0;
            }

            graph[ny][nx] = 1;

        }

        System.out.println(time);

    }

    static class Order {
        int time;
        String turnabout;

        Order(int time, String turnabout) {
            this.time = time;
            this.turnabout = turnabout;
        }

    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
