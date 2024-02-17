import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class BOJ17070_파이프_옮기기_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, map[][], answer;
    
    // DP로 푸는 경우
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int[][][] dp = new int[N][N][3]; // 0: 가로, 1: 세로, 2: 대각

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                // 가로
                if (j - 1 >= 0 && map[i][j] == 0)
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

                // 세로
                if (i - 1 >= 0 && map[i][j] == 0)
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

                // 대각선
                if (i - 1 >= 0 && j - 1 >= 0 && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0)
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }

        answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.print(answer);
    }

	
    // DFS로 푸는 경우
    public static void main2(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        answer = 0;
        DFS(0, 1, 0);
        
        System.out.print(answer);
    }
    
    public static void DFS(int r, int c, int direction) {
        if (r == N - 1 && c == N - 1) {
            answer++;
            return;
        }
        
        // 파이프가 가로로 놓여졌을 때 오른쪽으로 가는 경우
        if (direction == 0) {
            if (c + 1 < N && map[r][c + 1] == 0)
                DFS(r, c + 1, 0);
        }
        
        // 파이프가 세로로 놓여졌을 때 아래쪽으로 가는 경우
        if (direction == 1) {
            if (r + 1 < N && map[r + 1][c] == 0)
                DFS(r + 1, c, 1);
        }
        
        // 파이프가 대각선으로 놓여졌을 때
        if (direction == 2) {
            // 오른쪽으로 가는 경우
            if (c + 1 < N && map[r][c + 1] == 0)
                DFS(r, c + 1, 0);
            // 아래쪽으로 가는 경우
            if (r + 1 < N && map[r + 1][c] == 0)
                DFS(r + 1, c, 1);
        }
 
        // 파이프가 어떻게 놓여있던지 대각선 방향으로 가는 경우
        if (r + 1 < N && c + 1 < N && map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) {
            DFS(r + 1, c + 1, 2);
        }
    }
}
