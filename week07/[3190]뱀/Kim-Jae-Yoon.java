import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, l;
    static int[][] area;
    static char[] cmd;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static Deque<Point> snake;
    static int dir = 0;

    static void solution() throws IOException{
        for (int i = 0; i <= 10000; ++i) {
            if (cmd[i] == 'D') {
                dir = (dir + 1) % 4;
            }
            else if (cmd[i] == 'L') {
                dir = (dir + 3) % 4;
            }
            if(!canMove()) {
                System.out.println(i + 1);
                return;
            }
        }
    }

    //벽이나 자기 자신의 몸과 부딪치면 게임 즉시 끝
    static boolean isEnd(int x, int y) {
        if(x < 1 || y < 1 || x > n || y > n) return true;
        if(area[x][y] == -1) return true;
        return false;
    }

    static boolean canMove(){
        if(snake.isEmpty()) return false;
        Point cur = snake.peek();
        int nx = cur.x + dx[dir];
        int ny = cur.y + dy[dir];
        if(isEnd(nx, ny)) return false;
        snake.addFirst(new Point(nx, ny));
        if(area[nx][ny] > 0){
            area[nx][ny] = -1;
        }
        else if(area[nx][ny] == 0){
            Point del = snake.pollLast();
            area[del.x][del.y] = 0;
            area[nx][ny] = -1;
        }
        return true;
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        snake = new ArrayDeque<>();
        snake.add(new Point(1, 1));
        area = new int[n + 1][n + 1];
        area[1][1] = -1;
        cmd = new char[10001];
        for (int i = 1; i <= k; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            area[x][y] = 1;
        }
        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            cmd[x] = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
