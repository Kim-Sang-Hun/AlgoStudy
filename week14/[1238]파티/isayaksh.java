import java.io.*;
import java.util.*;

public class isayaksh {

	private static int N, M, X;
	private static List<Target>[] time;
	private static List<Target>[] reverseTime;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		time = new List[N+1];
		reverseTime = new List[N+1];
		for(int n = 1; n < N+1; n++) {
			time[n] = new ArrayList<Target>();
			reverseTime[n] = new ArrayList<Target>();
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			time[A].add(new Target(B, T));
			reverseTime[B].add(new Target(A, T));
		}
		
		int answer = 0;
		
		int[] departureToX = dijkstra(reverseTime);
		int[] xTodepartures = dijkstra(time);
		
		for(int n = 1; n < N+1; n++) {
			if(answer < departureToX[n] + xTodepartures[n]) answer = departureToX[n] + xTodepartures[n];
		}
		
		System.out.println(answer);
		
	}
	
	private static int[] dijkstra(List<Target>[] graph) {
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		Deque<Target> deque = new ArrayDeque<Target>();
		
		deque.add(new Target(X, 0));
		distance[X] = 0;
		
		while(!deque.isEmpty()) {
			Target target = deque.poll();
			
			for(Target nextTarget : graph[target.B]) {
				if(distance[nextTarget.B] < distance[target.B] + nextTarget.T) continue;
				distance[nextTarget.B] = distance[target.B] + nextTarget.T;
				deque.add(nextTarget);
			}
			
		}
		
		return distance;
	}
	
	static class Target {
		int B, T;
		
		public Target(int B, int T) {
			this.B = B;
			this.T = T;
		}
		
	}
	
}
