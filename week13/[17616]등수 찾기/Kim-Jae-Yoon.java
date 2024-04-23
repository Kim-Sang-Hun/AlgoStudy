import java.io.*;
import java.util.*;
/*
  Title: 등수 찾기
  Tier: Gold 3
  Algorithm: Topological Sort
  Constraint: 1 Second, 512MB
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, x, u, v;
	static List<Integer>[] indeg1, indeg2;

  /*
    위상 정렬을 사용하여 최고, 최저 등수를 측정하기 위해 두 개의 indegree 배열을 생성
  */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		indeg1 = new ArrayList[n + 1];
		indeg2 = new ArrayList[n + 1];
		for(int i = 1;i <= n; ++i) {
			indeg1[i] = new ArrayList<>();
			indeg2[i] = new ArrayList<>();
		}
		for(int i = 0;i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			indeg1[a].add(b);
			indeg2[b].add(a);
		}
	}

  /*
    ud 파라미터를 사용하여 메서드의 중복을 제거함
    ud == 0 -> 가능한 최고 등수
    ud == 1 -> 가능한 최저 등수
  */
	static int calculateUpperOrLower(int ud) {
		boolean[] vis = new boolean[n + 1];
		vis[x] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(x);
		int answer = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			++answer;
			if(ud == 1) {
				for(int nxt : indeg1[cur]) {
					if(vis[nxt]) continue;
					vis[nxt] = true;
					q.add(nxt);
				}
			} else {
				for(int nxt : indeg2[cur]) {
					if(vis[nxt]) continue;
					vis[nxt] = true;
					q.add(nxt);
				}
			}
		}
		return answer;
	}
	
	static void solution() {
		u = calculateUpperOrLower(0);
		v = n - calculateUpperOrLower(1) + 1;
		System.out.println(u + " " + v);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
}
