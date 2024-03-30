import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int[] rudolf_dx = {};
    private static final int[] rudolf_dy = {1, 1, 1, 1, 0, 0, 0, 0};

    private static final int[] santa_dx = {0, 0, -1, 1};
    private static final int[] santa_dy = {-1, 1, 0, 0};

    private static int N, M, P, C, D;
    
    private static int[] scores;
    private static int[] santaStatus; // (-1 : 탈락)

    private static Santa[] santas;
    private static Rudolf rudolf;

    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N+1][N+1];

        st = new StringTokenizer(br.readLine());
        int ry = Integer.parseInt(st.nextToken());
        int rx = Integer.parseInt(st.nextToken());
        rudolf = new Rudolf(rx, ry, C);
        board[ry][rx] = -1;
        
        scores = new int[P+1];
        santas = new Santa[P+1];
        santaStatus = new int[P+1];
        for(int p = 0; p < P; p++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            santas[no] = new Santa(sx, sy, D);
            board[sy][sx] = no;
        }

        for(int m = 0; m < M; m++) {
            // 1-1. 루돌프의 움직임
            Point way = getWayRudolf();

            board[rudolf.y][rudolf.x] = 0;

            rudolf.x = rudolf.x + way.x;
            rudolf.y = rudolf.y + way.y;

            // 1-2. 루돌프 -> 산타의 충돌
            if(board[rudolf.y][rudolf.x] > 0 && santaStatus[board[rudolf.y][rudolf.x]] != -1) {

                int no = board[rudolf.y][rudolf.x];

                // 기절
                santaStatus[no] = m+2;
                
                // 루돌프가 움직여서 충돌이 일어난 경우, 해당 산타는 C만큼의 점수를 얻게 됩니다. 
                scores[no] += C;

                // 산타는 루돌프가 이동해온 방향으로 C 칸 만큼 밀려나게 됩니다.
                Santa santa = santas[no];
                int nx = santa.x + way.x * C;
                int ny = santa.y + way.y * C;

                // 밀려난 위치가 게임판 밖이라면 산타는 게임에서 탈락됩니다.
                if(nx < 1 || nx > N || ny < 1 || ny > N) {
                    santaStatus[no] = -1;
                }

                else {
                    if(board[ny][nx] > 0) interaction(nx, ny, way.x, way.y);
                    board[ny][nx] = no;
                }

            }
            board[rudolf.y][rudolf.x] = -1;

            // 2-1. 산타의 움직임

            for(int p = 1; p < P+1; p++) {
                // 게임에서 제외된 산타 or 기절한 산타
                if(santaStatus[p] == -1 || santaStatus[p] > m) continue;

                Santa santa = santas[p];
                int no = board[santa.y][santa.x];

                // 루돌프와 가장 가까워지는 방향 찾기
                way = getWaySanta(santa);

                System.out.println("santa way : " + way.x + ", " + way.y);

                // 2-2. 산타 -> 루돌프의 충돌
                if(board[santa.y + way.y][santa.x + way.x] == -1) {
                    scores[no] += santa.D;

                    int dx = 0, dy = 0;
                    if(way.x == 1) dx = -1;
                    else if(way.x == -1) dx = 1;

                    if(way.y == 1) dy = -1;
                    else if(way.y == -1) dy = 1;

                    int nx = santa.x + dx * santa.D;
                    int ny = santa.y + dy * santa.D;

                    // 범위를 벗어난 경우!
                    if(nx < 1 || nx > N || ny < 1 || ny > N) {
                        santaStatus[no] = -1;
                    }

                    else {
                        if(board[ny][nx] != 0) interaction(ny, nx, way.x, way.y);
                        board[ny][nx] = no;
                    }
                }
            }



            for(int p = 1; p < P+1; p++) {
                if(santaStatus[p] != -1) scores[p] += 1;
            }

            System.out.println("[ Day : " + m + " ]");
            for(int y = 1; y < N+1; y++) {
                for(int x = 1; x < N+1; x++) {
                    System.out.print(board[y][x] + " ");
                }
                System.out.println();
            }
            System.out.println();

        }

        System.out.println(Arrays.toString(scores));
    }

    // 루돌프의 움직임
    private static Point getWayRudolf() {
        double distance = Integer.MAX_VALUE;
        Point way = new Point(0, 0);

        double sx, sy, dist;
        for(int no = 1; no < P+1; no++) {

            // 게임에서 탈락한 산타 게임에서 제외
            if(santaStatus[no] == -1) continue;

            // 산타(satnas[no])의 위치
            sx = santas[no].x;
            sy = santas[no].y;

            // 산타(satnas[no])와 루돌프(rudolf)의 거리
            dist = Math.pow(sx - rudolf.x, 2) + Math.pow(sy - rudolf.y, 2);
            
            if(dist < distance) {
                distance = dist;

                if(sx < rudolf.x) way.x = -1;
                if(sx == rudolf.x) way.x = 0;
                if(sx > rudolf.x) way.x = 1;

                if(sy < rudolf.y) way.y = -1;
                if(sy == rudolf.y) way.y = 0;
                if(sy > rudolf.y) way.y = 1;
            }  
        }
        return way;
    }

    private static Point getWaySanta(Santa santa) {

        Point way = new Point(0, 0);

        double distance = Double.MAX_VALUE;
        int nx, ny;
        for(int dir = 0; dir < 4; dir++) {
            nx = santa.x + santa_dx[dir];
            ny = santa.y + santa_dy[dir];

            if(nx < 1 || nx > N || ny < 1 || ny > N) continue;

            double dist = Math.pow(rudolf.x - nx, 2) + Math.pow(rudolf.y - ny, 2);

            if(dist < distance) {
                distance = dist;
                way.x = santa_dx[dir];
                way.y = santa_dy[dir];
            }
        }

        return way;
    }

    private static void interaction(int x, int y, int dx, int dy) {

        if(x+dx < 1 || x+dx > N || y+dy < 1 || y+dy > N) {
            santaStatus[board[y][x]] = -1;
            return;
        }

        if(board[y+dy][x+dx] != 0) interaction(x+dx, y+dy, dx, dy);

        board[y+dy][x+dx] = board[y][x];
        board[y][x] = 0;

    }

    static class Santa extends Point {
        int D;
        Santa(int x, int y, int D) {
            super(x, y);
            this.D = D;
        }
    }

    static class Rudolf extends Point {
        int C;
        Rudolf(int x, int y, int C) {
            super(x, y);
            this.C = C;
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
