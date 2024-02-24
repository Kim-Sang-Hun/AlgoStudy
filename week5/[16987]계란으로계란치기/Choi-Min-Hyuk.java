import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987_계란으로_계란치기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, durability[], weight[], answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		durability = new int[N];
		weight = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			durability[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}

		breakEgg(0, 0);
		System.out.print(answer);
	}
	
	// 왼쪽에서 1번째에 위치한 계란부터 하나씩 다른 계란들과 모두 부딪히는 조합으로 계란이 가장 많이 깨지는 경우 계산
	public static void breakEgg(int eggIndex, int brokenEggCount) {
		if (eggIndex == N) {
			answer = Math.max(answer, brokenEggCount);
			return;
		}
		
		// 들려고 하는 계란이 이미 깨졌거나 모든 계란이 이미 다 깨져있으면 넘어감
		if (durability[eggIndex] <= 0 || brokenEggCount == N - 1) {
			breakEgg(eggIndex + 1, brokenEggCount);
			return;
		}
		
		// 다른 계란들과 모두 부딪혀봄
		for (int i = 0; i < N; i++) {
			// 들고 있는 계란과 부딪히려고 하는 계란이 같거나 부딛히려고 하는 계란이 이미 깨져있으면 넘어감
			if (i == eggIndex || durability[i] <= 0)
				continue;
			
			durability[i] -= weight[eggIndex];
			durability[eggIndex] -= weight[i];
			
			brokenEggCount += (durability[eggIndex] <= 0 ? 1 : 0) + (durability[i] <= 0 ? 1 : 0);
			breakEgg(eggIndex + 1, brokenEggCount);
			
			brokenEggCount -= (durability[eggIndex] <= 0 ? 1 : 0) + (durability[i] <= 0 ? 1 : 0);
			durability[i] += weight[eggIndex];
			durability[eggIndex] += weight[i];
		}
	}
}
