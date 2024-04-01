import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
      // 겹치는 시험 점수를 제거하기 위해 Set을 사용한다
			Set<Integer> set = new HashSet<>();
			set.add(0);
      // 기존의 시험 점수에 새로운 시험 점수를 더한 값들을 Set에 추가로 넣어주면 새로운 시험 점수들을 전부 넣을 수 있다.
      // O(N^2)이다. (1+2+3+...+N-1번 연산);
			for (int i = 0; i < N; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				Set<Integer> clone = new HashSet<>(set);
				Iterator<Integer> iterator = clone.iterator();
				while (iterator.hasNext()) {
					set.add(iterator.next() + tmp);
				}
			}
			sb.append("#" + tc + " " + set.size() + "\n");
		}
		System.out.print(sb);
	}
}
