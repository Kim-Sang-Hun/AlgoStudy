import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int[] dx = {0, 0, 0, -1, 1};
    private static final int[] dy = {0, -1, 1, 0, 0};

    private static int[][][] priority; // [shark_no][current_dir][next_dir]
    private static int[][] sharkGraph;
    private static int[][] pheromoneGraph;

    private static int N, M, K;
    private static int time = 0;
    private static int shark_num;

    private static Shark[] sharks;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        shark_num = M;

        sharkGraph = new int[N+1][N+1];
        pheromoneGraph = new int[N+1][N+1];
        for(int y = 1; y < N+1; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 1; x < N+1; x++) {
                sharkGraph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        sharks = new Shark[M+1];
        findSharks();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < M+1; i++) {
            Shark shark = sharks[i];
            shark.d = Integer.parseInt(st.nextToken());
        }

        priority = new int[M+1][5][4];
        for(int m = 1; m < M+1; m++) {
            for(int i = 1; i < 5; i++) {
                priority[m][i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            }
        }

        // solution
        while(time < 1001 && 1 < shark_num) {

            // 1. 페로몬 지우기
            removePheromones();

            // 2. 이동하기
            move();

            // 3. 페로몬 뿌리기
            sprayPhereomones(); 

            time++;

        }

        System.out.println(time != 1001 ? time : -1);

    }

    // 상어 찾기
    private static void findSharks() {
        for(int y = 1; y < N+1; y++) {
            for(int x = 1; x < N+1; x++) {
                if(sharkGraph[y][x] != 0) {
                    sharks[sharkGraph[y][x]] = new Shark(x, y, sharkGraph[y][x], 0);
                    pheromoneGraph[y][x] = K+1;
                }
            }
        }
    }

    // 1. 상어 페로몬 지우기
    private static void removePheromones() {
        for(int y = 1; y < N+1; y++) {
            for(int x = 1; x < N+1; x++) {
                if(pheromoneGraph[y][x] == 0) continue;
                if(--pheromoneGraph[y][x] == 0) sharkGraph[y][x] = 0;
            }
        }
    }

    // 2. 상어 이동하기
    private static void move() {

        int nx, ny;

        loop:
        for(int i = 1; i < M+1; i++) {
            
            Shark shark = sharks[i];

            if(shark == null) continue;

            // 1. 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
            for(int dir : priority[shark.no][shark.d]) {
                nx = shark.x + dx[dir];
                ny = shark.y + dy[dir];
                
                if(nx < 1 || nx > N || ny < 1 || ny > N || sharkGraph[ny][nx] != 0) continue;

                shark.x = nx;
                shark.y = ny;
                shark.d = dir;

                continue loop;
            }

            // 2. 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다.
            for(int dir : priority[shark.no][shark.d]) {
                nx = shark.x + dx[dir];
                ny = shark.y + dy[dir];
                
                if(nx < 1 || nx > N || ny < 1 || ny > N || sharkGraph[ny][nx] != shark.no) continue;

                shark.x = nx;
                shark.y = ny;
                shark.d = dir;
                
                continue loop;
            }

        }

    }

    // 3. 상어 페로몬 뿌리기
    private static void sprayPhereomones() {
        for(int i = 1; i < M+1; i++) {

            Shark shark = sharks[i];

            if(shark == null) continue;

            if(sharkGraph[shark.y][shark.x] != 0 && sharkGraph[shark.y][shark.x] < shark.no) {
                sharks[i] = null;
                shark_num--;
                continue;
            }

            pheromoneGraph[shark.y][shark.x] = K+1;
            sharkGraph[shark.y][shark.x] = shark.no;

        }
    }

    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Shark extends Point{
        int no, d; // priority, direction

        Shark(int x, int y, int no, int d) {
            super(x, y);
            this.no = no;
            this.d = d;
        }
    }

}
