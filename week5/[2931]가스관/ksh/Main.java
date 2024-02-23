import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};

    private static final char[] pipe = {0, '1', '2', '3', '4', '|', '-', '+'};

    // direction[현재 진행 방향][현재 도착한 파이프] = next direction
    private static final int[][] direction = {
        {-1, 1, -1, -1, 3, 0, -1, 0},
        {-1, -1, -1, 0, 2, -1, 1, 1},
        {-1, -1, 1, 3, -1, 2, -1, 2},
        {-1, 2, 0, -1, -1, -1, 3, 3}
    };

    // pipeConnection[사방][파이프모양]
    private static final boolean[][] pipeConnection = {
        {false, true, false, false, true, true, false, true},
        {false, false, false, true, true, false, true, true},
        {false, false, true, true, false, true, false, true},
        {false, true, true, false, false, false, true, true}
    };

    private static int R, C;
    private static int mx, my, md;
    private static int targetX, targetY, targetPipe;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new int[R][C];

        for(int r = 0; r < R; r++) {
            String[] input = br.readLine().split("");

            for(int c = 0; c < C; c++) {
                if(input[c].equals(".")) graph[r][c] = 0;
                if(input[c].equals("|")) graph[r][c] = 5;
                if(input[c].equals("-")) graph[r][c] = 6;
                if(input[c].equals("+")) graph[r][c] = 7;
                if(input[c].equals("M")) graph[r][c] = 8;
                if(input[c].equals("Z")) graph[r][c] = 9;
                if(input[c].matches("[1-4]")) graph[r][c] = Integer.parseInt(input[c]);
            }

        }

        findMoscow();

        move(mx, my, md);

        System.out.println((targetY + 1) + " " + (targetX + 1) + " " + pipe[targetPipe]);

    }

    private static void findMoscow() {
        for(int y = 0; y < R; y++) {
            for(int x = 0; x < C; x++) {
                if(graph[y][x] == 8) {
                    int nx, ny;
                    mx = x;
                    my = y;
                    
                    for(int i = 0; i < 4; i++) {
                        nx = x + dx[i];
                        ny = y + dy[i];
                        
                        if(nx < 0 || nx >= C || ny < 0 || ny >= R || direction[i][graph[ny][nx]] == -1) continue;

                        md = i;
                        return;

                    }

                }
            }
        }
    }

    private static void move(int x, int y, int dir) {

        if(graph[y][x] == 0) {
            targetX = x;
            targetY = y;
            return;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(graph[ny][nx] == 9) {
            return;
        }

        move(nx, ny, direction[dir][graph[ny][nx]]);
        

    }

    private static char findTargetPipe() {
        int dir1, dir2;
        int nx1, ny1, nx2, ny2;
        int p1, p2;


        // return 7;
        

        // return 6;


        // return 5;
        

        // return 1, 2, 3, 4;
        for(int i = 0; i < 4; i++) {
            dir1 = i;
            dir2 = (i+1)%4;

            nx1 = targetX + dx[dir1];
            ny1 = targetY + dy[dir1];

            nx2 = targetX + dx[dir2];
            ny2 = targetY + dy[dir2];

            if(nx1 < 0 || nx1 >= C || ny1 < 0 || ny1 >= R) continue;
            if(nx2 < 0 || nx2 >= C || ny2 < 0 || ny2 >= R) continue;

            p1 = graph[ny1][nx1];
            p2 = graph[ny2][nx2];

            if(pipeConnection[dir1][p1] && pipeConnection[dir2][p2]) return '@';
        }



        return ' ';
    }

}
