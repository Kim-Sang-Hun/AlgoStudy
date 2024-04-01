import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int [][] a, dp;

    static void input() throws IOException{
    	n = Integer.parseInt(br.readLine());
    	a = new int[n + 2][n + 2];
    	dp = new int[n + 2][n + 2];
    	for(int i = 1;i <= n; ++i) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 1;j <= i; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    }

    static void solution(){
    	dp[1][1] = a[1][1];
    	for(int i = 0;i <= n; ++i) {
        	for(int j = 1;j <= n; ++j) {
        		dp[i + 1][j] = Math.max(dp[i + 1][j], Math.max(dp[i][j], dp[i][j - 1]) + a[i + 1][j]);
    		}
    	}
    	int answer = 0;
    	for(int i = 1;i <= n; ++i) {
    		answer = Math.max(answer, dp[n][i]);
    	}
    	System.out.println(answer);
    }

    public static void main(String[] args) throws IOException{
        input();
        solution();
    }
}
