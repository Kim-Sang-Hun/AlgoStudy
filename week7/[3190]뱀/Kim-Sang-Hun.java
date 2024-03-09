package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 큐로 풀어준다. 뱀의 위치에 따라 움직이면서 큐에 넣어주는데
// 사과라면 큐에서 뽑지 않고 넘어가고
// 사과가 아니리면 큐에서 하나 뽑는다.
// 만약 뱀이 가려는 곳이 큐에 존재한다면
// 게임을 끝낸다.
public class JUN3190_뱀 {

    static int N, K, L, time;
    static int[][] map;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static Queue<Point> snake = new LinkedList<>();
    static List<Operator> op = new ArrayList<>();

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    static class Operator {
        int time;
        String dir;

        public Operator(int time, String dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            op.add(new Operator(time, dir));
        }
        bfs();
        System.out.println(time);
    }

    private static void bfs() {
        snake.add(new Point(1, 1));
        int y = 1;
        int x = 1;
        int dir = 0;
        int opIdx = 0;

        while (true) {
            ++time;
            y += dy[dir];
            x += dx[dir];
            if (!isValid(y, x)) {
                break;
            }
            if (opIdx < op.size()) {
                Operator operator = op.get(opIdx);
                if (operator.time == time) {
                    if (operator.dir.equals("L")) {
                        if (dir == 0) {
                            dir = 3;
                        } else {
                            dir -= 1;
                        }
                    } else {
                        if (dir == 3) {
                            dir = 0;
                        } else {
                            dir += 1;
                        }
                    }
                    ++opIdx;
                }
            }

            if (map[y][x] == 0) {
                snake.poll();
            } else if (map[y][x] == 1) {
                map[y][x] = 0;
            }
            snake.add(new Point(y, x));
        }
    }

    private static boolean isValid(int y, int x) {
        if (y > N || y < 1 || x > N || x < 1 || snake.contains(new Point(y, x))) return false;
        return true;
    }
}
