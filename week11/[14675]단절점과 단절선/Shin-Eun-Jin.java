import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 단절점과단절선 {
	static int N, q;
	static int[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nodes = new int[N+1];

		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			nodes[node1]++;
			nodes[node2]++;
		}

		q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken()); // 질의
			int k = Integer.parseInt(st.nextToken()); // 몇번째

			if (t == 1) { // 단절점 질의일 경우
				if (nodes[k] == 1) {
					System.out.println("no");
				} else {
					System.out.println("yes");
				}

			} else { // 단절선 질의일 경우
				System.out.println("yes");
			}

		}

	}

}
