import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1563_개근상_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, dp[][][];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		dp = new int[N][2][3];// 2번째 인덱스는 지각을 했는지 안 했는지, 3번째 인덱스는 연속 결석 수
		// 2번째 인덱스가 1이면 지각을 이미 한 번 한 상태라는 뜻이다.
		
		// O(출석), L(지각), A(결석), 총 3가지
		if (N == 1) {
			System.out.println("3");
		}
		else {
			dp = new int[N][2][3];// 2번째 인덱스는 지각을 했는지 안 했는지, 3번째 인덱스는 연속 결석 수, 2번째 인덱스가 1이면 지각을 이미 한 번 한 상태라는 뜻
			dp[1][0][0] = 2; // OO, AO
			dp[1][0][1] = 1; // OA
			dp[1][0][2] = 1; // AA
			dp[1][1][0] = 3; // LO, OL, AL
			dp[1][1][1] = 1; // LA
			dp[1][1][2] = 0;
			
			for (int i = 2; i < N; i++) {
				dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % 1000000;
				dp[i][0][1] = dp[i - 1][0][0];
				dp[i][0][2] = dp[i - 1][0][1]; // i번째 날이 2연속 결속이려면 i-1번째 날은 결석이어야 함
				dp[i][1][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2] + dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % 1000000;
				
				// 지각을 안한 상태인 i-1번째 날을 지각을 한 상태인 i번째 날에 더해줌
				dp[i][1][1] = dp[i - 1][1][0];
				dp[i][1][2] = dp[i - 1][1][1];
			}
			
			long sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += dp[N - 1][0][i];
				sum += dp[N - 1][1][i];
			}
			
			System.out.print(sum % 1000000);
		}
	}
}
