import java.io.*;
import java.util.*;

public class Main {

    public static int[][] move = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static void main(String[] args) throws IOException {
        // INPUT
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N][M];
        for(int y = 0; y < N; y++) {
            String[] str = br.readLine().split("");
            for(int x = 0; x < M; x++) {
                graph[y][x] = Integer.parseInt(str[x]);
            }
        }

        // RESULT
        System.out.println(solution(N, M, graph));

    }

    public static int solution(int N, int M, int[][] graph) {
        int[][][] visited = new int[2][N][M];
        int nx, ny, nz;

        Queue<Pair> queue = new ArrayDeque<Pair>();
        visited[0][0][0] = 1;
        visited[1][0][0] = 1;
        queue.add(new Pair(0, 0, 0));
        while(!queue.isEmpty()) {
            Pair pair = queue.poll();

            // 목적지 도달
            if(pair.getX() == M-1 && pair.getY() == N-1) {
                return visited[pair.getZ()][pair.getY()][pair.getX()];
            }

            for(int i = 0; i < 4; i++) {
                nx = pair.getX() + move[i][0];
                ny = pair.getY() + move[i][1];
                nz = pair.getZ();

                // 맵에서 벗어난 경우
                if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

                // 이동할 수 있는 곳(0)에 한번도 방문하지 않았다면
                if(graph[ny][nx] == 0 && visited[nz][ny][nx] == 0) {
                    visited[nz][ny][nx] = visited[nz][pair.getY()][pair.getX()] + 1;
                    queue.add(new Pair(nx, ny, pair.getZ()));
                }

                // 벽(1)이 존재하고, 벽을 한번 부술 수 있는 경우
                if(graph[ny][nx] == 1 && nz == 0 && visited[nz+1][ny][nx] == 0) {
                    visited[nz+1][ny][nx] = visited[nz][pair.getY()][pair.getX()] + 1;
                    queue.add(new Pair(nx, ny, 1));
                }
            }
        }

        return -1;
    }
}

class Pair {
    private int x;
    private int y;
    private int z;

    public Pair(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }

    // @Override
    // public String toString() {
    //     return "x : " + x + ", y : " + y + ", z : " + z;
    // }
}