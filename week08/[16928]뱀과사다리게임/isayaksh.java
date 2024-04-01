import java.io.*;
import java.util.*;

public class isayaksh {

    private static int[] board = new int[101];
    private static int[] dp = new int[101];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int x, y;
        for(int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            board[x] = y;
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        dfs(1);

        System.out.println(dp[100]);
        
    }

    public static void dfs(int x) {

        if(x == 100) return;

        int nx;

        for(int dx = 1; dx <= 6; dx++) {

            nx = x + dx;

            if(nx > 100) continue;

            // 사다리 or 뱀
            if(board[nx] != 0 && dp[board[nx]] > dp[x] + 1) {
                dp[board[nx]] = dp[x] + 1;
                dfs(board[nx]);
            }

            // 일반 주사위
            if(board[nx] == 0 && dp[nx] > dp[x] + 1) {
                dp[nx] = dp[x] + 1;
                dfs(nx);
            }

        }

    }

}
