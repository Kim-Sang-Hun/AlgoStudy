import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] board;
    static int[][] rotation;
    static int min = Integer.MAX_VALUE;
    static int n, m;
    static boolean[] visited;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotation = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rotation[i][0] = Integer.parseInt(st.nextToken());
            rotation[i][1] = Integer.parseInt(st.nextToken());
            rotation[i][2] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[k];
        result = new int[k];
        permutation(0, k);

        System.out.println(min);
    }

    public static void permutation(int idx, int k) {
        if (idx == k) {
            int[][] copy = new int[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(board[i], 0, copy[i], 0, m);
            }
            findMin(copy);
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[idx] = i;
                permutation(idx + 1, k);
                visited[i] = false;
            }
        }
    }

    public static void findMin(int[][] copy) {
        for (int i = 0; i < result.length; i++) {
            int lx = rotation[result[i]][0] - rotation[result[i]][2] - 1;
            int ly = rotation[result[i]][1] - rotation[result[i]][2] - 1;
            int rx = rotation[result[i]][0] + rotation[result[i]][2] - 1;
            int ry = rotation[result[i]][1] + rotation[result[i]][2] - 1;
            rotate(lx, ly, rx, ry, copy);
        }
        rowcal(copy);
    }

    public static void rowcal(int[][] copy) {
        for (int i = 0; i < copy.length; i++) {
            int sum = Arrays.stream(copy[i]).sum();
            min = Math.min(min, sum);
        }
    }

    public static void rotate(int lx, int ly, int rx, int ry, int[][] copy) {
        if (lx == rx && ly == ry) {
            return;
        }

        int[] temp = new int[3];
        temp[0] = copy[lx][ry];
        temp[1] = copy[rx][ry];
        temp[2] = copy[rx][ly];

        for (int i = ry; i > ly; i--) {
            copy[lx][i] = copy[lx][i - 1];
        }
        for (int i = rx; i > lx; i--) {
            if (i == lx + 1) copy[i][ry] = temp[0];
            else copy[i][ry] = copy[i - 1][ry];
        }
        for (int i = ly; i < ry; i++) {
            if (i == ry - 1) copy[rx][i] = temp[1];
            else copy[rx][i] = copy[rx][i + 1];
        }
        for (int i = lx; i < rx; i++) {
            if (i == rx - 1) copy[i][ly] = temp[2];
            else copy[i][ly] = copy[i + 1][ly];
        }
        rotate(lx + 1, ly + 1, rx - 1, ry - 1, copy);
    }
}
