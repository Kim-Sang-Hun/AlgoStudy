import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// 한 정점에서 가장 먼 거리의 node 구하고 구한 node로부터 가장 먼 거리를 return
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int V;
	static int[] vtd;
	static HashMap<Integer, ArrayList<int[]>> nodes;

	public static void main(String[] args) throws Exception {
		V = Integer.parseInt(br.readLine());
		nodes = new HashMap<>();
		vtd = new int[V + 1];
		Arrays.fill(vtd, -1);
		
		for (int v = 0; v < V; v++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			ArrayList<int[]> value = new ArrayList<>();
			for (int i = 1; i < arr.length - 1; i += 2) {
				value.add(new int[] { arr[i], arr[i + 1] });
			}
			nodes.put(arr[0], value);
		}
		
		vtd[1] = 0;
		dfs(1);
		dfs(getMax()[0]);
		
		System.out.println(getMax()[1]);
	}

	static void dfs(int node) {
		for (int[] arr : nodes.get(node)) {
			if (vtd[arr[0]] == -1) {
				vtd[arr[0]] = vtd[node] + arr[1];
				dfs(arr[0]);
			}
		}
	}

	static int[] getMax() {
		int index = 1, len = vtd[index];
		for (int i = 2; i <= V; i++) {
			if (vtd[i] > len) {
				index = i;
				len = vtd[i];
			}
		}
		Arrays.fill(vtd, -1);
		vtd[index] = 0;
		return new int[] { index, len };
	}
}
