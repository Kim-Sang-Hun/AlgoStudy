import java.io.*;
import java.util.*;

public class Main {

    public static int answer = 0;
    public static int N;
    public static int M;
    public static int[][] graph;
    public static int[][] move = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    public static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);
        graph = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                visited[y][x] = true;
                dfs(x, y, 0, graph[y][x]);
                visited[y][x] = false;
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int depth, int sum) {
        if(depth == 3) {
            answer = Math.max(answer, sum);
            return;
        }
        int nx, ny;
        for(int i = 0; i < 4; i++) {
            nx = x + move[i][0];
            ny = y + move[i][1];

            if(nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx]){
                continue;
            }

            if(depth == 1) {
                visited[ny][nx] = true;
                dfs(x, y, depth+1, sum + graph[ny][nx]);
                visited[ny][nx] = false;
            }

            visited[ny][nx] = true;
            dfs(nx, ny, depth+1, sum + graph[ny][nx]);
            visited[ny][nx] = false;

        }

    }
}