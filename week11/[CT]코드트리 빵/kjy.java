import java.awt.Point;
import java.io.*;
import java.util.*;

/*
  추후 리팩터링 필요
  리팩터링 후 설명란을 추가하도록 하겠습니다.
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, arrivalCnt, answer;
    static int[][] area;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    static class Human{
        Point cur, dst;
        Human(int x, int y){
            dst = new Point(x, y);
        }
    }
    static class Info{
        int x, y, moveCnt = 0;

        Info(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            moveCnt = dist;
        }
        Info(Point cur){
            this.x = cur.x;
            this.y = cur.y;
        }
    }
    static Human[] human;
    static boolean[] arrived;

    static void input() throws IOException {
        arrivalCnt = answer = 0;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arrived = new boolean[m + 1];
        area = new int[n][n];
        human = new Human[m + 1];
        for(int i = 0;i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < n; ++j) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1;i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            human[i] = new Human(x, y);
        }
    }

    static int bfs(int i){
        Deque<Point> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][n];
        //목적지에서 시작해서 출발지로 가장 빠르게 도달하는 경우가 결국 우리가 찾는 목적 방향의 반대 방향이다
        vis[human[i].dst.x][human[i].dst.y] = true;
        q.add(human[i].dst);
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir = 0;dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(!range(nx, ny)) continue;
                if(nx == human[i].cur.x && ny == human[i].cur.y) {
                    if(dir == 0 || dir == 3) return 3 - dir;
                    if(dir == 1) return 2;
                    return 1;
                }
                if(vis[nx][ny] || area[nx][ny] == -1) continue;
                vis[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
        return -1;
    }

    static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static void moveAll(int cnt) {
        for(int i = 1;i <= cnt; ++i) {
            if(arrived[i]) continue;
            int nDir = bfs(i);
            if(nDir == -1) continue;
            human[i].cur.x += dx[nDir];
            human[i].cur.y += dy[nDir];
        }
    }

    static void checkarriveConv(int cnt) {
        for(int i = 1;i <= cnt; ++i) {
            if(arrived[i]) continue;
            if(human[i].cur.x == human[i].dst.x && human[i].cur.y == human[i].dst.y) {
                arrived[i] = true;
                area[human[i].cur.x][human[i].cur.y] = -1;
                ++arrivalCnt;
            }
        }
    }

    static void setBaseCamp(int i) {
        Deque<Info> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][n];
        //목적지에서 시작해서 출발지로 가장 빠르게 도달하는 베이스캠프를 탐색
        vis[human[i].dst.x][human[i].dst.y] = true;
        q.add(new Info(human[i].dst));
        int dist = (int)1e9;
        while(!q.isEmpty()) {
            Info cur = q.poll();
            for(int dir = 0;dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(!range(nx, ny)) continue;
                if(vis[nx][ny] || area[nx][ny] == -1) continue;
                if(dist < cur.moveCnt) continue;
                if(area[nx][ny] == 1) {
                    if (human[i].cur == null || dist > cur.moveCnt) {
                        human[i].cur = new Point(nx, ny);
                        dist = cur.moveCnt;
                    } else {
                        if(human[i].cur.x > nx){
                            human[i].cur = new Point(nx, ny);
                        } else if (human[i].cur.x == nx) {
                            if (human[i].cur.y > ny) {
                                human[i].cur = new Point(nx, ny);
                            }
                        } else{
                            continue;
                        }
                    }
                    continue;
                }
                vis[nx][ny] = true;
                q.add(new Info(nx, ny, cur.moveCnt + 1));
            }
        }
        area[human[i].cur.x][human[i].cur.y] = -1;
    }

    static void solution(){
        while(arrivalCnt < m) {
            //step 1
            moveAll(Math.min(answer, m));
            //step 2
            checkarriveConv(Math.min(answer, m));
            //step 3
            if(++answer <= m) {
                setBaseCamp(answer);
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
