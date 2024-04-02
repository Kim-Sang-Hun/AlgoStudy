import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int[] dx = {1, 0, -1};
    private static final int[] dy = {0, 1, 0};


    private static int N, M;
    private static int[][] board;

    private static int answer = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for(int n = 0; n < N; n++) {
            board[n] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }

        System.out.println(dynamicProgramming());
        
    }

    private static int dynamicProgramming() {

        int dp[][] = new int [N][M];
        dp[0][0] = board[0][0];

        // 1층은 오른쪽(→) 이동만 가능
        for(int x = 1; x < M; x++) {
            dp[0][x] = dp[0][x-1] + board[0][x];
        }

        int[] tmp1, tmp2;
        for(int y = 1; y < N; y++) {

            tmp1 = new int[M];
            tmp2 = new int[M];

            for(int x = 0; x < M; x++) {
                int value = dp[y-1][x] + board[y][x];
                dp[y][x] = tmp1[x] = tmp2[x] = value;
            }

            for(int x = 0; x < M-1; x++) {
                if(tmp1[x+1] < tmp1[x] + board[y][x+1]) tmp1[x+1] = tmp1[x] + board[y][x+1];
            }

            for(int x = M-1; x > 0; x--) {
                if(tmp2[x-1] < tmp2[x] + board[y][x-1]) tmp2[x-1] = tmp2[x] + board[y][x-1];
            }

            for(int x = 0; x < M; x++) {
                if(dp[y][x] < tmp1[x]) dp[y][x] = tmp1[x];
                if(dp[y][x] < tmp2[x]) dp[y][x] = tmp2[x];
            }
        }

        return dp[N-1][M-1];
        
    }

}
