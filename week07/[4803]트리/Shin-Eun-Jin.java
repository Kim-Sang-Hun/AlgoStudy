import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int n, m;
	static int edgeNum, nodeNum;
	static boolean[] isVisit;
	static ArrayList<ArrayList<Integer>> list;
	static boolean isTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0)
				break;

			isVisit = new boolean[n + 1];
			list = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				list.add(new ArrayList<>());
			}

			// 리스트 입력
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				list.get(a).add(b);
				list.get(b).add(a);
			}

			isTree = true;
			int treeNum = 0;
			for (int i = 1; i <= n; i++) {
				if (isVisit[i])
					continue;

				edgeNum = 0;
				nodeNum = 0;
				dfs(i);

				if ((nodeNum - 1) == edgeNum / 2)
					treeNum++;
			}

			// 출력
			sb.append("Case " + tc + ": ");
			if (treeNum == 0) {
				sb.append("No trees.\n");

			} else if (treeNum == 1) {
				sb.append("There is one tree.\n");

			} else {
				sb.append("A forest of " + treeNum + " trees.\n");
			}

			tc++;
		}
		System.out.println(sb);

	}

	static void dfs(int node) {
		isVisit[node] = true;
		nodeNum++;

		for (int i : list.get(node)) {
			edgeNum++;

			if (isVisit[i]) {
				continue;
			}

			dfs(i);
		}
	}
}
