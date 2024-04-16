import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_피리부는사나이 {
    static int N, M;
    static int cnt;
    static char[][] map;
    static int[][] nums;
    static boolean[][] isVisit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static HashMap<Character, Integer> dirs = new HashMap<>() {
        {
            put('U', 0);
            put('D', 1);
            put('L', 2);
            put('R', 3);
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행의 수
        M = Integer.parseInt(st.nextToken()); // 열의 수
        map = new char[N][M];
        nums = new int[N][M];
        isVisit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isVisit[i][j])
                    continue;

                dfs(i, j);
            }
        }

        System.out.println(cnt);
    }

    public static int dfs(int row, int col) {

        isVisit[row][col] = true;
        char dir = map[row][col];
        int nextRow = row + dr[dirs.get(dir)];
        int nextCol = col + dc[dirs.get(dir)];

        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
            return ++cnt;
        }

        if(isVisit[nextRow][nextCol]) {
            nums[row][col] = nums[nextRow][nextCol] == 0 ? ++cnt : nums[nextRow][nextCol];
            return nums[row][col];
        }

        nums[row][col] = dfs(nextRow, nextCol);
        return nums[row][col];
    }
}
