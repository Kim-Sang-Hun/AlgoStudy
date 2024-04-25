import java.util.*;
import java.io.*;

public class JUN2412_암벽등반_김주희 {
	static int n, T, answer;
	static Home[] homes;
	
	static class Home implements Comparable<Home>{
		int x;
		int y;
		
		public Home(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Home o) {
			if(this.y == o.y) return this.x - o.x;
			return this.y - o.y;
		}
	}
	
	static class Route{
		int idx;
		int dist;
		
		public Route(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
	
	private static void bfs() {
		Queue<Route> q = new LinkedList<>();
		boolean visited[] = new boolean[n+1];
		q.add(new Route(0,0));
		visited[0] = true;
		
		while(!q.isEmpty()) {
			Route cur = q.poll();
			
			if( homes[cur.idx].y == T) {
				answer = cur.dist;
				break;
			}
			
			for(int i = 1; i <= n; i++) {
				if(visited[i]) continue;
				if(Math.abs(homes[cur.idx].x - homes[i].x) <= 2 &&
						Math.abs(homes[cur.idx].y - homes[i].y) <= 2) {
					visited[i] = true;
					q.add(new Route(i,cur.dist+1));
				}
				
				if(homes[i].y - homes[cur.idx].y > 2) break;
				// y값이 현재 값보다 2차이 이상 나면 반복 멈춤.
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		homes = new Home[n+1];
		homes[0] = new Home(0,0);
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			homes[i] = new Home(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(homes);
		
		answer = -1;
		bfs();
		
		System.out.println(answer);

	}

}
