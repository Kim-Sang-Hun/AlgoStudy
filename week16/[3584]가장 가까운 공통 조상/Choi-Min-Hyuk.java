import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ3584_가장가까운공통조상_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	
	static int[][] parent; // 부모 노드 관련 메모이제이션 배열
	static int[] depth; // 깊이 관련 배열
	static boolean[] check; // 루트 노드 관련 배열
	static ArrayList<ArrayList<Integer>> tree;
	static int T, N, size, root;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			tree = new ArrayList<>();
			check = new boolean[N + 1];
			depth = new int[N + 1];
			size = getHeight(N);
			parent = new int[N + 1][size];
			
			for (int j = 0; j <= N; j++)
				tree.add(new ArrayList<>());
			
			for (int j = 1; j < N; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				tree.get(A).add(B);
				tree.get(B).add(A);
				check[B] = true;
			}
			
			getRoot(N);
			setDepth(1, root, 0);
			setParent(N);
			
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(LCA(a, b)).append(lineSeparator);
		}
		
		System.out.print(sb);
	}

	// 트리의 최대 높이 구하는 함수
	static int getHeight(int n) {
		return (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
	}

	// 트리의 루트 노드 구하는 함수
	static void getRoot(int n) {
		for (int i = 1; i <= n; i++) {
			if (!check[i]) {
				root = i;
				return;
			}
		}
	}

	// 트리의 깊이와 부모 노드 구성하는 함수
	static void setDepth(int d, int cur, int p) {
		depth[cur] = d;
		for (int next : tree.get(cur)) {
			if (p != next) {
				parent[next][0] = cur;
				setDepth(d + 1, next, cur);
			}
		}
	}

	// Parent[][] 메모이제이션 구성하는 함수
	static void setParent(int n) {
		for (int i = 1; i < size; i++) {
			for (int j = 1; j <= n; j++) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];
			}
		}
	}

	// LCA 진행하는 함수
	static int LCA(int a, int b) {
		int da = depth[a];
		int db = depth[b];
		// da가 항상 깊이가 높다고 가정
		if (db > da) {
			int temp = b;
			b = a;
			a = temp;
		}
		// 이분 탐색으로 깊이 맞추기
		for (int i = size - 1; i >= 0; i--) {
			if (depth[a] - Math.pow(2, i) >= depth[b])
				a = parent[a][i];
		}
		
		// 깊이를 맞추었을 때 같은 노드일 때는 해당 노드 최소 공통 조상 노드
		if (a == b)
			return a;
		
		// 이분 탐색으로 깊이를 맞춘 두 노드를 올라가기 진행
		for (int i = size - 1; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		return parent[a][0];
	}
}
