import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};

    private static int N;
    private static int[][] graph;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];

        for(int r = 1; r < N+1; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c < N+1; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // int answer = Integer.MAX_VALUE;

        // for(int y = 1; y <= N; y++) {
        //     for(int x = 1; x <= N; x++) {
        //         for(int d1 = 1; d1 < N - 1; d1++) {
        //             for(int d2 = 1; d2 < N - d1; d2++) {
        //                 if(isPossible(x, y, d1, d2)) {
        //                     System.out.println("[x : " + x + ", y : " + y + ", d1 : " + d1 + ", d2 : " + d2 + "]");
        //                     answer = Math.min(answer, seperate(x, y, d1, d2));
        //                 }
        //             }
        //         }
        //     }
        // }

        // System.out.println(answer);

        seperate(2, 4, 1, 1);

    }

    private static boolean isPossible(int x, int y, int d1, int d2) {
        return (x+d1+d2 <= N) && (0 < y-d1) && (y+d2 <= N);
    }

    private static int seperate(int x, int y, int d1, int d2) {
        int[] population = new int[5];
        visited = new boolean[N+1][N+1];

        /*
         * (x, y), (x+1, y-1), ..., (x+d1, y-d1)
         * (x, y), (x+1, y+1), ..., (x+d2, y+d2)
         * (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
         * (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
         */

        // 5번 선거구
        for(int dd1 = 0; dd1 < d1; dd1++) {

            if(!visited[y-dd1][x+dd1]) {
                visited[y-dd1][x+dd1] = true;
                population[4] += graph[y-dd1][x+dd1];
            }

            if(!visited[y+d2-dd1][x+d2+dd1]) {
                visited[y+d2-dd1][x+d2+dd1] = true;
                population[4] += graph[y+d2-dd1][x+d2+dd1];
            }
            
        }
        for(int dd2 = 0; dd2 < d2; dd2++) {

            if(!visited[y+dd2][x+dd2]) {
                visited[y+dd2][x+dd2] = true;
                population[4] += graph[y+dd2][x+dd2];
            }

            if(!visited[y-d1+dd2][x+d1+dd2]) {
                visited[y-d1+dd2][x+d1+dd2] = true;
                population[4] += graph[y-d1+dd2][x+d1+dd2];
            }
        }

        visited[y][x+1] = true;
        population[4] += dfs(x+1,y);

        
        for(int r = 1; r < N+1; r++) {
            for(int c = 1; c < N+1; c++) {

                if(visited[r][c]) continue;
                
                // 1번 선거구
                else if(1 <= r && r < x + d1 && 1 <= c && c <= y) {
                    population[0] += graph[r][c];
                }

                // 2번 선거구
                else if(1 <= r && r <= x+d2 && y < c && c <= N) {
                    population[1] += graph[r][c];
                }

                // 3번 선거구
                else if(x+d1 <= r && r <= N && 1 <= c && c < y-d1+d2) {
                    population[2] += graph[r][c];
                }

                // 4번 선거구
                else if(x+d2 < r && r <= N && y-d1+d2 <= c && c <= N) {
                    population[3] += graph[r][c];
                }

            }
        }

        System.out.println(Arrays.toString(population));

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for(int p : population) {
            if(max < p) max = p;
            if(min > p) min = p;
        }

        return max - min;

    }

    private static int dfs(int x, int y) {
        int value = graph[y][x];
        int nx, ny;
        for(int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) continue;
            visited[ny][nx] = true;

            value = dfs(nx, ny);

        }

        return value;
    }

}