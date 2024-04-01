package march4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class JUN1865_웜홀_김주희 {
	static int N, M, W, dist[];
	static Vertex[] Vs;
	
	static class Vertex{
		int S,E,T;

		public Vertex(int s, int e, int t) {
			S = s;
			E = e;
			T = t;
		}

		@Override
		public String toString() {
			return "Vertex [S=" + S + ", E=" + E + ", T=" + T + "]";
		}

	}
	
	private static void bellman_ford(int start) {
		// 시작 노드에 대해 초기화
		dist[start] = 0;
		for (int i = 0; i < N; i++) {
			for (Vertex v : Vs) {
				if(dist[v.S] != Integer.MAX_VALUE && dist[v.E] > dist[v.S] + v.T) {
					dist[v.E] = dist[v.S] + v.T;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			Vs = new Vertex[2*M + W];
			
			int j = 0;
			// 도로는 양방향
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				Vs[j++] = new Vertex(n1,n2,t);
				Vs[j++] = new Vertex(n2,n1,t);
			}
			// 웜홀은 한 방향
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				Vs[j++] = new Vertex(s,e,-t);
			}
			
		
			boolean flag = false;
			outerLoop :
			for (int i = 1; i < N+1; i++) {
				List<Vertex> last = new ArrayList<>(); // 시작지점에 도착할 수 있는 노드 저장
				
				for (Vertex v : Vs) {
					if(v.E == i) last.add(v);
				}
				//최단 거리 테이블 초기화
				dist = new int[N+1];
				for (int k = 0; k < N+1; k++) {
					dist[k] = Integer.MAX_VALUE;
				}
				
				bellman_ford(i);
				
				for(Vertex v : last) {
					if(dist[v.S] + v.T < 0) {
						flag = true;
						break outerLoop;
					}
				}

			}
			if(flag) System.out.println("YES");
			else System.out.println("NO");

		}
	}

}
