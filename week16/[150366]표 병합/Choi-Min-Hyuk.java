import java.util.ArrayList;
import java.util.StringTokenizer;

// Union-Find
class Solution {
	static StringTokenizer st;
	static int n = 2500;
	static int groups[];
	static String values[];

	public String[] solution(String[] commands) {
		groups = new int[n];
		values = new String[n];
		ArrayList<String> answers = new ArrayList<>();

		for (int i = 0; i < n; i++)
			groups[i] = i;

		for (int i = 0; i < commands.length; i++) {
			st = new StringTokenizer(commands[i]);

			int r, c;
			String v;
			switch (st.nextToken()) {
			case "UPDATE":
				String v1 = st.nextToken();
				String v2 = st.nextToken();

				if (st.hasMoreTokens()) {
					String value = st.nextToken();
					r = Integer.parseInt(v1) - 1;
					c = Integer.parseInt(v2) - 1;
					values[find(r * 50 + c)] = value;
				}
				
				else {
					for (int j = 0; j < n; j++) {
						if (values[find(j)] != null && values[find(j)].equals(v1)) {
							values[find(j)] = v2;
						}
					}
				}
				break;

			case "MERGE":
				int r1 = Integer.parseInt(st.nextToken()) - 1;
				int c1 = Integer.parseInt(st.nextToken()) - 1;
				int r2 = Integer.parseInt(st.nextToken()) - 1;
				int c2 = Integer.parseInt(st.nextToken()) - 1;

				int num1 = r1 * 50 + c1;
				int num2 = r2 * 50 + c2;

				if (values[find(num1)] == null && values[find(num2)] != null) {
					int temp = num1;
					num1 = num2;
					num2 = temp;
				}

				union(num1, num2);
				break;

			case "UNMERGE":
				r = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken()) - 1;

				int g = find(r * 50 + c);
				v = values[g];

				for (int j = 0; j < n; j++)
					find(j);

				for (int j = 0; j < n; j++) {
					if (find(j) == g) {
						groups[j] = j;

						if (j == r * 50 + c)
							values[j] = v;
						else
							values[j] = null;
					}
				}
				break;

			case "PRINT":
				r = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken()) - 1;

				v = values[find(r * 50 + c)];
				answers.add(v == null ? "EMPTY" : v);
				break;
			}

		}

		String[] answer = new String[answers.size()];
		for (int i = 0; i < answers.size(); i++) 
			answer[i] = answers.get(i);
		
		return answer;
	}

	static int find(int idx) {
		if (idx == groups[idx]) 
			return idx;

		return groups[idx] = find(groups[idx]);
	}

	static void union(int g1, int g2) {
		g1 = find(g1);
		g2 = find(g2);

		if (g1 == g2)
			return;

		values[g2] = null;
		groups[g2] = g1;
	}
}
