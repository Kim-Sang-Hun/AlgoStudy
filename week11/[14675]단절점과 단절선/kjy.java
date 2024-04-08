import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] edge;
	static int[][] sequence;
	static int n, a, b, q, t, k;
	
	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		edge = new ArrayList[n + 1];
		for(int i = 1;i <= n; ++i) {
			edge[i] = new ArrayList<>();
		}
		sequence = new int[n + 1][2];
		for(int i = 1;i <= n - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			edge[a].add(b);
			edge[b].add(a);
			sequence[i][0] = a;
			sequence[i][1] = b;
		}
	}

  //가장 간단하게 리프 노드인지 여부를 따지는 문제라고 생각합니다.
  //리프 노드를 연결하는 간선인지, 리프 노드인지 여부에 따라 yes와 no가 정해짐을 바로 알 수 있습니다.
	static void solution() throws IOException{
		q = Integer.parseInt(br.readLine());
		for(int i = 0;i < q; ++i) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			//k번째 정점이 단절점인지 질의
			if(t == 1) {
				if(edge[k].size() > 1) {
					sb.append("yes\n");
				} else {
					sb.append("no\n");
				}
			}
			//k번째 정점이 단절선인지 질의
			else {
				int u = sequence[k][0];
				int v = sequence[k][0];
				if(u == v || edge[u].size() == 1 || edge[v].size() == 1) {
					sb.append("yes\n");
				} else {
					sb.append("no\n");
				}
			}
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
}
