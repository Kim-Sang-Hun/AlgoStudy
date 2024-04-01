import java.io.*;
import java.util.*;

public class Main {

    public static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N][M];
        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(M, N, graph));

    }

    public static int solution(int M, int N, int[][] graph) {
        int day = 0;
        int nx, ny;
        Queue<Pair> queue = new ArrayDeque<>();

        // 1. 익은 토마토 찾기
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(graph[y][x] == 1) {
                    queue.add(Pair.create(x, y, 0));
                }
            }
        }

        // 2. 모든 토마토 익히기!
        while(!queue.isEmpty()) {
            Pair pair = queue.poll();
            for(int i = 0; i < 4; i++) {
                nx = pair.getX() + move[i][0];
                ny = pair.getY() + move[i][1];
                if(nx < 0 || nx >= M || ny < 0 || ny >= N || graph[ny][nx] != 0) continue;
                graph[ny][nx] = 1;
                queue.add(Pair.create(nx, ny, pair.getCount()+1));
                if(day < pair.getCount() + 1) {
                    day = pair.getCount() + 1;
                }
            }
        }

        // 3. 익지 않은 토마토가 있는지 확인!
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(graph[y][x] == 0) {
                    return -1;
                }
            }
        }

        return day;
    }
}

class Pair {
    private int x;
    private int y;
    private int count;

    private Pair() {
    }

    public static Pair create(int x, int y, int count) {
        Pair pair = new Pair();
        pair.x = x;
        pair.y = y;
        pair.count = count;
        return pair;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCount() {
        return count;
    }
}
