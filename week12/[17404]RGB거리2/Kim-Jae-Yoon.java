import java.util.*;
import java.io.*;
/*
  Title: RGB거리 2
  Tier: Gold 4
  Algorithm: Dynamic Programming
  Constraint: 0.5 Second, 128MB
  Additional Condition: Circular Structure
*/
public class Main{
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int MAX = (int)1e9;
	static int n, m, answer = MAX;
	static int[][] color, dp;
	
	static void solution() {
		for(int index = 1;index <= 3; ++index) {
			dp = new int[n + 1][4];
			setInit(index);
			getNextProcess();
			getAnswer(index);
		}
		System.out.println(answer);
	}
	
	static void getNextProcess() {
		for(int i = 2;i <= n; ++i) {
			dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][3]) + color[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][3]) + color[i][2];
			dp[i][3] = Math.min(dp[i - 1][1], dp[i - 1][2]) + color[i][3];
		}
	}
	
	static void getAnswer(int index) {
		for(int i = 1;i <= 3; ++i) {
			if(i == index) continue;
			answer = Math.min(answer, dp[n][i]);
		}
	}
	
	static void setInit(int index) {
		for(int i = 1;i <= 3; ++i) {
			if(i != index) dp[1][i] = MAX;
			else dp[1][i] = color[1][i];
		}
	}
	
	static void input() throws IOException{
		n = Integer.parseInt(br.readLine());
		color = new int[n + 1][4];
		for(int i = 1;i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1;j <= 3; ++j) {
				color[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
}
