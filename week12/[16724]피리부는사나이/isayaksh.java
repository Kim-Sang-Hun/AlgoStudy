import java.util.*;
import java.io.*;

public class isayaksh {

    private static final Map<Character, Point> dir = new HashMap<>(){
        {
            put('U', new Point(0, -1));
            put('D', new Point(0, 1));
            put('L', new Point(-1, 0));
            put('R', new Point(1, 0));
        }
    };

    private static int N, M;
    private static char[][] board;
    private static int[] parent;
    
    public static void main(String[] args) throws IOException {

        // ======== input ========

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for(int n = 0; n < N; n++) {
            board[n] = br.readLine().toCharArray();
        }

        parent = new int[N*M];
        for(int nm = 0; nm < N*M; nm++) {
            parent[nm] = nm;
        }

        // ======== union & find ========

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {

                Point d = dir.get(board[y][x]);

                int nx = x + d.x;
                int ny = y + d.y;

                if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

                union(x + y * M, nx + ny * M);
            }
        }

        int count = 0;
        boolean[] used = new boolean[N*M];

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                int target = find(x + y * M);
                if(used[target]) continue;
                used[target] = true;
                count++;
            }
        }

        System.out.println(count);

    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int find(int x) {
        if(x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parent[Math.max(x, y)] = Math.min(x, y);
    }

}
