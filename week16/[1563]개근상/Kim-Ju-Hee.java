package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUN1563_개근상_김주희 {
	static int N, dp[][][];
	static final int MOD = 1_000_000;
	
	private static int dfs(int depth, int late, int absentLong) {
		//조건 넘어가면 0됨
		if(late >= 2) return 0;
		if(absentLong == 3) return 0;
		
		if(depth == N) return 1; // 마지막날
		
		if(dp[depth][late][absentLong] == -1)
			return dp[depth][late][absentLong] = (
													dfs(depth+1, late, 0) + // 출석한 경우
													dfs(depth+1, late+1, 0) + // 지각한 경우
													dfs(depth+1, late, absentLong+1)) // 결석한 경우
													% MOD;
		
		return dp[depth][late][absentLong];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][2][3];
		
		//방문안한거 -1
        for(int i = 0; i < N+1; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        
        int answer = dfs(0, 0, 0);	
		System.out.println(answer);

	}

}
