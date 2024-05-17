import java.io.*;
import java.util.*;

public class isayaksh {

    private static final long DIV = 1000000L;

    private static int N;
    private static long dp[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long [N+1][2][3];

        for(int i = 0; i < N+1; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1L;
                }
            }
        }

        System.out.println(dfs(0, 0, 0) % DIV);
        
    }

    private static long dfs(int day, int late, int absent) {

        if(late == 2 || absent == 3)
            return 0;

        if(day == N)
            return 1;

        if(dp[day][late][absent] == -1) 
            return dp[day][late][absent] = (dfs(day+1, late, 0) + dfs(day+1, late+1, 0) + dfs(day+1, late, absent+1)) % DIV;
        
        return dp[day][late][absent];
        
    }
    
}
