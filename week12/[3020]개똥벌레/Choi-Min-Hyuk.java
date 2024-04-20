import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3020_개똥벌레_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, H;
	static int minBreakCount = Integer.MAX_VALUE;
	static int sectionCount = 0;

	public static void main(String args[]) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		int stalactiteCounts[] = new int[H + 1]; // 해당하는 index 높이 종유석의 수
		int stalagmiteCounts[] = new int[H + 1]; // 해당하는 index 높이 석순의 수

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (i % 2 == 1)
				stalactiteCounts[num]++;
			else
				stalagmiteCounts[num]++;
		}

		for (int i = H - 1; i > 0; i--) {
			stalactiteCounts[i] += stalactiteCounts[i + 1];
			stalagmiteCounts[i] += stalagmiteCounts[i + 1];
		}

		for (int i = 1; i < H + 1; i++) {
			int breakCount = stalactiteCounts[i] + stalagmiteCounts[H - i + 1];
			if (breakCount < minBreakCount) {
				minBreakCount = breakCount;
				sectionCount = 1;
			} else if (breakCount == minBreakCount)
				sectionCount++;

		}

		System.out.print(minBreakCount + " " + sectionCount);
	}
}
