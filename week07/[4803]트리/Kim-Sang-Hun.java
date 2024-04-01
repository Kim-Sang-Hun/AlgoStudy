import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * union-find로 풀어주었다.
 * 사이클이 발생했을 경우 루트를 0으로 지정했다.
 * 그래서 a와 b가 있을 때 루트값이 작은 것을 루트로 잡아야 루프가 발생했을 때 모두 0으로 처리된다.
 * 마지막에 parent값이 0인 것은 세지 않는다.
 * 
 */
public class JUN4803_트리 {

	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = 0;
		while (true) {
			++tc;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if (n == 0) break;
			makeSet(n);
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i < parent.length; i++) {
				if (find(i) != 0)
				set.add(find(i));
			}
			if (set.size() == 0) {
				sb.append("Case " + tc + ": No trees." + System.lineSeparator());
			} else if (set.size() == 1) {
				sb.append("Case " + tc + ": There is one tree." + System.lineSeparator());
			} else {
				sb.append("Case " + tc + ": A forest of " + set.size() + " trees." + System.lineSeparator());
			}
		}
		System.out.println(sb);
	}
	
	private static void makeSet(int n) {
		parent = new int[n + 1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
	}

	private static int find(int a) {
		if (parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(parent[a]);
		int bRoot = find(parent[b]);

		if (aRoot == bRoot) {
			parent[bRoot] = 0;
		} else if (aRoot > bRoot) parent[aRoot] = parent[bRoot];
		else parent[bRoot] = parent[aRoot];
	}
}
