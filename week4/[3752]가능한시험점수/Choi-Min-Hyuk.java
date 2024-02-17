import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA3752_가능한_시험점수_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	static StringTokenizer st;
	static Set<Integer> set = new HashSet<Integer>();
	static int T, N, answer;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			set.clear();
			set.add(0);
			answer = 0;

			N = Integer.parseInt(br.readLine());
			int[] points = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				points[i] = Integer.parseInt(st.nextToken());

			set.add(0);
			for (int i = 0; i < N; i++)
				addScoreSet(points[i]);

			answer = set.size();
			sb.append("#").append(testcase).append(" ").append(answer).append(lineSeparator);
		}

		System.out.print(sb);
	}
	
	// 각 문제의 배점으로 받을 수 있는 조합은 이전 문제까지의 조합에 다음 문제의 배점을 모두 더한 값을 추가하는 것 + set으로 중복 제외
	static void addScoreSet(int num) {
		Set<Integer> tempSet = new HashSet<Integer>();
		tempSet.addAll(set);

		Iterator<Integer> iter = tempSet.iterator();
		while (iter.hasNext()) {
			set.add(iter.next() + num);
		}
	}
}
