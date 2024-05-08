import java.io.*;
import java.util.*;

public class isayaksh {

    private static final int dx[] = {1, -1, 0, 0};
    private static final int dy[] = {0, 0, 1, -1};

    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // init board
        board = new int[N][M];
        for(int n = 0; n < N; n++) {
            char[] number = br.readLine().toCharArray();
            for(int m = 0; m < M; m++) {
                board[n][m] = number[m] == '1' ? -1 : 0;
            }
        }
        
        // init parent
        parent = new int[N*M];
        for(int i = 0; i < N*M; i++) parent[i] = i;
        
        // init visited
        visited = new boolean[N][M];
        
        
        
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(board[y][x] != 0) continue;
                bfs(x, y);
            }
        }

        Set<Integer> set = new HashSet<Integer>();
        int nx, ny;

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(board[y][x] != -1) {
                    sb.append(0);
                    continue;
                }

                int count = 1;
                set.clear();

                for(int i = 0; i < 4; i++) {
                    nx = x + dx[i];
                    ny = y + dy[i];

                    if(nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] == -1) continue;
                    int num = find(nx + ny * M);
                    set.add(num);
                }

                for(int num : set) {
                    count = (count + board[num/M][num%M]) % 10;
                }
                sb.append(count);
            }
            sb.append("\n");
        }

        System.out.println(sb);

        
    }

    private static int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parent[Math.max(x, y)] = Math.min(x, y);
    }

    private static void bfs(int x, int y) {
        int count = 1;

        Deque<Integer> deque = new ArrayDeque<Integer>();
        deque.add(x + y * M);

        Deque<Integer> connectedArea = new ArrayDeque<Integer>();
        connectedArea.add(x + y * M);

        visited[y][x] = true;

        while(!deque.isEmpty()) {
            int num = deque.poll();

            for(int i = 0; i < 4; i++) {
                int nx = (num % M) + dx[i];
                int ny = (num / M) + dy[i];

                if(nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] != 0) continue;

                visited[ny][nx] = true;
                count = (count + 1) % 10;

                deque.add(nx + ny * M);
                connectedArea.add(nx + ny * M);

                union(x + y * M, nx + ny * M);
            }
        }

        for(int num : connectedArea) {
            board[num/M][num%M] = count;
        }

    }

    
}
