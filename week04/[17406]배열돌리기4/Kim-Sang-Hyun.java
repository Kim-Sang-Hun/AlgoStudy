import java.util.*;
import java.io.*;

public class Main {
    
    public static int[][] arr;
    public static int[][] orders;
    public static boolean[] used;

    public static int answer = Integer.MAX_VALUE;
    public static int N;
    public static int M;
    public static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        orders = new int[K][3];
        used = new boolean[K];

        for(int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }

        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            orders[k][0] = Integer.parseInt(st.nextToken());
            orders[k][1] = Integer.parseInt(st.nextToken());
            orders[k][2] = Integer.parseInt(st.nextToken());
        }

        permutation(0);

        System.out.println(answer);

    }

    public static void rotate(int r, int c, int s) {
        int tmp;
        r--; c--;
        
        for(int range = 1; range <= s; range++) {

            tmp = arr[r-range][c-range];

            // ⬆
            for(int nr = r-range; nr < r+range; nr++) {
                arr[nr][c-range] = arr[nr+1][c-range];
            }

            // ⬅
            for(int nc = c-range; nc < c+range; nc++) {
                arr[r+range][nc] = arr[r+range][nc+1];
            }

            // ⬇
            for(int nr = r+range; nr > r-range; nr--) {
                arr[nr][c+range] = arr[nr-1][c+range];
            }

            // ➡
            for(int nc = c+range; nc > c-range; nc--) {
                arr[r-range][nc] = arr[r-range][nc-1];
            }

            arr[r-range][c-range+1] = tmp;

        }
    }

    public static void rollback(int r, int c, int s) {
        int tmp;
        r--; c--;
        
        for(int range = 1; range <= s; range++) {

            tmp = arr[r-range][c-range];

            // ⬅
            for(int nc = c-range; nc < c+range; nc++) {
                arr[r-range][nc] = arr[r-range][nc+1];
            }

            // ⬆
            for(int nr = r-range; nr < r+range; nr++) {
                arr[nr][c+range] = arr[nr+1][c+range];
            }

            // ➡
            for(int nc = c+range; nc > c-range; nc--) {
                arr[r+range][nc] = arr[r+range][nc-1];
            }

            // ⬇
            for(int nr = r+range; nr > r-range; nr--) {
                arr[nr][c-range] = arr[nr-1][c-range];
            }

            arr[r-range+1][c-range] = tmp;

        }
    }

    public static void permutation(int depth) {
        if(depth == K) {
            minRowSum();
            return;
        }

        for(int k = 0; k < K; k++) {
            if(used[k]) continue;

            used[k] = true;
            rotate(orders[k][0], orders[k][1], orders[k][2]);

            permutation(depth+1);

            used[k] = false;
            rollback(orders[k][0], orders[k][1], orders[k][2]);

        }

    }

    public static void minRowSum() {
        int rowSum;

        for(int y = 0; y < N; y++) {
            rowSum = 0;
            for(int x = 0; x < M; x++) {
                rowSum+=arr[y][x];
            }
            answer = Math.min(answer, rowSum);
        }

    }

    public static void printArr() {
        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= M; x++) {
                System.out.print(arr[y-1][x-1] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}