import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N;
	static Egg[] eggList;
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		eggList = new Egg[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			eggList[i] = new Egg(S, W);
		}

		maxCnt = Integer.MIN_VALUE;
		dfs(0, 0);

		System.out.println(maxCnt);
	}

	static void dfs(int depth, int cnt) {

		if (depth == N) {
			maxCnt = Math.max(maxCnt, cnt);
			return;
		}

		Egg holdedEgg = eggList[depth];

		// 손에 쥔 계란이 깨져있을 경우 넘어감
		if (holdedEgg.isBreak || (cnt == N - 1)) {
			dfs(depth + 1, cnt);
			return;
		}

		for (int i = 0; i < N; i++) {
			Egg otherEgg = eggList[i];

			// 계란이 깨져있거나, 현재 들고있는 계란의 인덱스 값일 경우
			if (otherEgg.isBreak || i == depth)
				continue;

			// 계란 치기
			otherEgg.S -= holdedEgg.W;
			holdedEgg.S -= otherEgg.W;

			int curCnt = 0;
			// 계란 깨져있는 지 확인
			if (otherEgg.S <= 0) {
				otherEgg.isBreak = true;
				curCnt++;
			}
			if (holdedEgg.S <= 0) {
				holdedEgg.isBreak = true;
				curCnt++;
			}

			// 다음으로 넘어가기
			dfs(depth + 1, cnt + curCnt);

			// 초기화
			otherEgg.S += holdedEgg.W;
			holdedEgg.S += otherEgg.W;
			otherEgg.isBreak = false;
			holdedEgg.isBreak = false;
		}
	}

	static class Egg {
		int S;
		int W;
		boolean isBreak;

		public Egg(int S, int W) {
			this.S = S;
			this.W = W;
			this.isBreak = false;
		}

	}
}
