package april3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN17404_RGB거리2_김주희 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] cost = new int[N][3];
        int[][] dp = new int[N][3];

        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	cost[i][0] = Integer.parseInt(st.nextToken());
        	cost[i][1] = Integer.parseInt(st.nextToken());
        	cost[i][2] = Integer.parseInt(st.nextToken());
		}
        
        int answer = 1000*1000+1;
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < 3; j2++) {
					dp[j][j2] = 1000*1000+1;
				}
			}
        	
        	dp[0][i] = cost[0][i];
        	
        	for (int j = 1; j < N; j++) {
				dp[j][0] = Math.min(dp[j-1][1]+cost[j][0], dp[j-1][2]+cost[j][0]); 
				dp[j][1] = Math.min(dp[j-1][0]+cost[j][1], dp[j-1][2]+cost[j][1]); 
				dp[j][2] = Math.min(dp[j-1][1]+cost[j][2], dp[j-1][0]+cost[j][2]); 
			}
        	
        	for (int j = 0; j < 3; j++) {
				if(i==j) continue;
				answer = Math.min(answer, dp[N-1][j]);
			}
			
		}
        
        System.out.println(answer);

    }

}
